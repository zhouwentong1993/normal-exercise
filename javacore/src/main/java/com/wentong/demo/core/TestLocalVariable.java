package com.wentong.demo.core;

public class TestLocalVariable {

    static String s = "abc";

    public static void main(String[] args) {
        String s1 = s;
        System.out.println(s1);
        s = "bca";
        System.out.println(s1);
    }
}
