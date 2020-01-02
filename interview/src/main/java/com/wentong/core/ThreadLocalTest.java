package com.wentong.core;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("a");
        threadLocal.set("b");
        threadLocal.set("c");
        threadLocal.set("d");
        threadLocal.set("e");
        System.out.println(threadLocal.toString());
        System.out.println(threadLocal.get());
    }
}
