package com.spring.entity;

import com.spring.spring.annotation.Component;
import com.spring.spring.annotation.Value;
import lombok.Data;

import org.springframework.stereotype.Repository;
@Data
@Component
public class Order {
    @Value("xxx123")
    private String orderId;
    @Value("1000.0")
    private Float price;
}
