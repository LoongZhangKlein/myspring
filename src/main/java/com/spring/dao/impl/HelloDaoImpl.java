package com.spring.dao.impl;

import com.spring.dao.HelloDao;

public class HelloDaoImpl implements HelloDao {
    @Override
    public int[] getArrays(){
        int[] a=new int[]{1,2,3,4,5};
        return a;
    }
}
