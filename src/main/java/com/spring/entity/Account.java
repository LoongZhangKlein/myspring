package com.spring.entity;

import com.spring.spring.annotation.Autowired;
import com.spring.spring.annotation.Component;
import com.spring.spring.annotation.Qualifier;
import com.spring.spring.annotation.Value;
import lombok.Data;

import org.springframework.stereotype.Repository;

@Data
@Component
public class Account {
        @Value("1")
        private Integer id;
        @Value("张三")
        private String name;
        @Value("18")
        private Integer age;
        @Autowired
        @Qualifier("order")
        private Order myOrder;

}
