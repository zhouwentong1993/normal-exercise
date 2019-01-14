package com.wentong.demo;

import org.junit.Test;

public class TestThreadLocal {

    @Test
    public void testOutOfMemory() throws Exception {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        for (int i = 0; i < 100000; i++) {
            threadLocal.set("aaa");
        }
        System.out.println(threadLocal);
    }

    @Test
    public void testExtendThreadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hello world");

        Thread thread = new Thread(() -> {
            threadLocal.set("aaa");
            System.out.println("value:" + threadLocal.get());
        });
        thread.start();

        System.out.println("main:" + threadLocal.get());

    }

    @Test
    public void testPut() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread(() -> {
                threadLocal.set("thread:" + finalI);
            }).start();
        }
        System.out.println(threadLocal.get());
    }
}
