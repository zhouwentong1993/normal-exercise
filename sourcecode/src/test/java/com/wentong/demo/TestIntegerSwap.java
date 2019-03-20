package com.wentong.demo;

import java.lang.reflect.Field;

public class TestIntegerSwap {
    public static void main(String[] args) throws Exception{
        Integer a = 1;
        Integer b = 2;
        System.out.println("before a=" + a + ",b=" + b);
//        swap(a, b);
        Integer[] a1 = {a};
        Integer[] b1 = {b};
        swap(a1, b1);
        System.out.println("after a=" + a1[0] + ",b=" + b1[0]);
    }

    private static void swap(Integer a, Integer b) throws Exception{
        Integer temp = new Integer(a);
        Field value = a.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(a, b);

        Field value2 = a.getClass().getDeclaredField("value");
        value2.setAccessible(true);
        value2.set(b, temp);
    }

    private static void swap(Integer[] a, Integer[] b) {
        int temp = a[0];
        a[0] = b[0];
        b[0] = temp;
    }

}
