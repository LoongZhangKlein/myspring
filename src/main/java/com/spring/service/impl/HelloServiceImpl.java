package com.spring.service.impl;

import com.spring.dao.impl.HelloDaoImpl;
import com.spring.factory.BeanFactory;
import com.spring.service.HelloService;

public class HelloServiceImpl implements HelloService {
    /**
     * 之前的写法
     * 不可重用
     *
     * private HelloDaoImpl helloDao= new HelloDaoImpl();
     */
    private HelloDaoImpl helloDao= (HelloDaoImpl) BeanFactory.getDao("helloDao");

    /**
     * 验证是否是单例模式
     */
    public HelloServiceImpl(){
        for (int i = 0; i < 6; i++) {
            System.out.println(BeanFactory.getDao("helloDao"));
        }
    }

    @Override
    public int[] getArrays() {
        int[] arrays = helloDao.getArrays();
        return arrays;
    }
}
