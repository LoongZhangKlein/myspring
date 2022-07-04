package com.spring.spring.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author DragonZhang
 */
@Target(ElementType.TYPE)
public @interface Component {
    String value() default "";
}
