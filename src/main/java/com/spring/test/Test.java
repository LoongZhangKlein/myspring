package com.spring.test;

import com.spring.spring.MyAnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        /*//加载IoC容器
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.spring.entity");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(applicationContext.getBeanDefinitionCount());
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
            System.out.println(applicationContext.getBean(beanDefinitionName));
        }*/

       // 使用myspring测试
        MyAnnotationConfigApplicationContext applicationContext = new MyAnnotationConfigApplicationContext("com.spring.entity");
        System.out.println(applicationContext.getBeanDefinitionCount());
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName:beanDefinitionNames) {
            System.out.println(beanDefinitionName);
            System.out.println(applicationContext.getBean(beanDefinitionName));

        }

    }
}
