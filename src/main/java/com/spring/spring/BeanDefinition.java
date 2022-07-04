package com.spring.spring;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
/**
 * 添加包含所有字段的有参构造函数
 */
@AllArgsConstructor
public class BeanDefinition {
    private String beanName;
    private Class beanClass;
}
