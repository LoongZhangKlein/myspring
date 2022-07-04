package com.spring.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ElementType.TYPE：允许被修饰的注解作用在类、接口和枚举上
 *
 * ElementType.FIELD：允许作用在属性字段上
 *
 * ElementType.METHOD：允许作用在方法上
 *
 * ElementType.PARAMETER：允许作用在方法参数上
 *
 * ElementType.CONSTRUCTOR：允许作用在构造器上
 *
 * ElementType.LOCAL_VARIABLE：允许作用在本地局部变量上
 *
 * ElementType.ANNOTATION_TYPE：允许作用在注解上
 *
 * ElementType.PACKAGE：允许作用在包上
 */
@Target(ElementType.FIELD)
/**
 * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    String value() default "";
}
