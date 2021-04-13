package com.wentong.demo.core;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(nextId::getAndIncrement);

    public static int getNextValue() {
        return threadLocal.get();
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println("threadName:" + Thread.currentThread().getName() + " get value:" + getNextValue());
                }
            });
            thread.start();
            thread.join();
        }
    }
}
