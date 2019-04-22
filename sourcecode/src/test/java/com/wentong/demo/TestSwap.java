package com.wentong.demo;

import java.lang.reflect.Field;

public class TestSwap {

    public static void main(String[] args) throws Exception{
        Integer a = 1;
        Integer b = 2;
        System.out.println("before a = " + a + ",b = " + b);
        swap(a, b);
        System.out.println("after a = " + a + ",b = " + b);

    }

    public static void swap(Integer a,Integer b) throws Exception{
        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);
        int temp = a.intValue();
        value.set(a, b.intValue());
        value.set(b, new Integer(temp));
    }

}
