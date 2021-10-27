package com.wentong.demo.core;

public class VolatileTest {

    public static volatile int counter = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(counter);
    }

    private static void increase() {
        counter++;
    }

}
