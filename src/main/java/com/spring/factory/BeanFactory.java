package com.spring.factory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
    private static Properties properties;
    private static Map<String,Object> cache=new HashMap<>(16);
    static {
        String beanName = "application.properties";
        properties = new Properties();
        try {
            properties.load(BeanFactory.class.getClassLoader().getResourceAsStream(beanName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过反射进行获取类
     * @return
     */
    public static Object getDao(String beanName) {
        /**
         * 双重校验模式
         * 保证多线程下共享资源的安全性
         */
        //先判断缓存中是否存在bean
        if(!cache.containsKey(beanName)){
            synchronized (BeanFactory.class){
                if(!cache.containsKey(beanName)){
                    //将bean存入缓存
                    //反射机制创建对象
                    try {
                        String value = properties.getProperty(beanName);
                        Class clazz = Class.forName(value);
                        Object object = clazz.getConstructor(null).newInstance(null);
                        cache.put(beanName, object);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return cache.get(beanName);

    }
}
