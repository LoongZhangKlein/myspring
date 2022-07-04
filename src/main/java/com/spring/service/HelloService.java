package com.spring.service;

import org.springframework.stereotype.Component;

@Component("service")
public interface HelloService {
    int[] getArrays();
}
