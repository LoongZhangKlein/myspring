package com.spring.spring;


import com.spring.utils.MyTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author DragonZhang
 */
public class MyAnnotationConfigApplicationContext {
    // ioc容器
    private Map<String,Object> ioc = new HashMap<>();
    private List<String> beanNames = new ArrayList<>();
    public MyAnnotationConfigApplicationContext(String pack){
        //遍历包，找到目标类(原材料)
        Set<BeanDefinition> beanDefinitions = findBeanDefinitions(pack);
        //根据原材料创建bean
        createObject(beanDefinitions);
        //自动装载
        autowireObject(beanDefinitions);
    }

    /**
     * 按照Autowired自动装载各个属性值
     * @param beanDefinitions
     */
    private void autowireObject(Set<BeanDefinition> beanDefinitions) {
        Iterator<BeanDefinition> iterator = beanDefinitions.iterator();
        while (iterator.hasNext()) {
            BeanDefinition beanDefinition = iterator.next();
            Class clazz = beanDefinition.getBeanClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField:declaredFields){
                Autowired annotation = declaredField.getAnnotation(Autowired.class);
                if (annotation!=null){
                    Qualifier qualifier = declaredField.getAnnotation(Qualifier.class);
                    if (qualifier!=null){
                        //byName方式注入
                        String beanName = qualifier.value();
                        // 获取对应的Bean
                        Object bean = getBean(beanName);
                        String fieldName = declaredField.getName();
                        String methodName="set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                        try {
                            Method method = clazz.getMethod(methodName, declaredField.getType());
                            Object object = getBean(beanDefinition.getBeanName());
                            method.invoke(object, bean);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }else{
                        //ByType方式注入
                    }

                }
            }

        }
    }

    /**
     * 根据原材料创建Bean
     * @param beanDefinitions
     */
    private void createObject(Set<BeanDefinition> beanDefinitions) {
        Iterator<BeanDefinition> iterator = beanDefinitions.iterator();
        while (iterator.hasNext()) {
            BeanDefinition beanDefinition = iterator.next();
            Class clazz = beanDefinition.getBeanClass();
            String beanName = beanDefinition.getBeanName();
            try {
                //创建对象
                Object object = clazz.getConstructor().newInstance();
                /**
                 * getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
                 * getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段
                 */
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field declaredField:declaredFields) {
                    Value valueAnnotation = declaredField.getAnnotation(Value.class);
                    if (valueAnnotation!=null){
                        // 获取添加注解的属性值
                        String value = valueAnnotation.value();
                        // 获取添加注解的属性名字
                        String fieldName = declaredField.getName();
                        // 拼装set
                        String methodName = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
                        // 获取方法类型
                        Method method = clazz.getMethod(methodName,declaredField.getType());
                        //完成数据类型转换
                        Object val = null;
                        switch (declaredField.getType().getName()){
                            case "java.lang.Integer":
                                val = Integer.parseInt(value);
                                break;
                            case "java.lang.String":
                                val = value;
                                break;
                            case "java.lang.Float":
                                val = Float.parseFloat(value);
                                break;
                        }
                        // 向指定的类中添加相应的属性
                        method.invoke(object, val);
                    }
                }
                //存入缓存
                ioc.put(beanName, object);
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

    /**
     * 遍历目包 找到目标类
     * @param pack
     * @return
     */
    private Set<BeanDefinition> findBeanDefinitions(String pack) {
        // 获取包下的所有类
        Set<Class<?>> classes = MyTools.getClasses(pack);
        //遍历
        Iterator<Class<?>> iterator = classes.iterator();
        //创建存放容器
        Set<BeanDefinition> beanDefinitions = new HashSet<>();
        while (iterator.hasNext()) {
            //遍历类
            Class<?> clazz = iterator.next();
            // 查看是否添加注解
            Component componentAnnotation = clazz.getAnnotation(Component.class);
            if (componentAnnotation!=null){
                String beanName = componentAnnotation.value();
                // 如果其中的bean中的属性值是空的话只返回改bean的名字
            if ("".equals(beanName)){
                /**
                 * org.springframework.context.event.internalEventListenerFactory
                 * 此处精髓相当于把上边这段替换成internalEventListenerFactory
                 *然后把类名首字母变成小写
                 *
                 */
                String packageName=clazz.getPackage().getName()+".";
                String className = clazz.getName().replace(packageName, "");
                beanName=className.substring(0,1).toLowerCase()+className.substring(1);
            }
                //3、将这些类封装成BeanDefinition，装载到集合中
                beanDefinitions.add(new BeanDefinition(beanName, clazz));
                beanNames.add(beanName);
            }
        }
        return beanDefinitions;
    }
    public Object getBean(String beanName){
        return ioc.get(beanName);
    }
    public String[] getBeanDefinitionNames(){
        return beanNames.toArray(new String[0]);
    }
    public Integer getBeanDefinitionCount(){
        return beanNames.size();
    }
}
