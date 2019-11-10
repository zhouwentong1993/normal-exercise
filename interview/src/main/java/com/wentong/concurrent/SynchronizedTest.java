package com.wentong.concurrent;

public class SynchronizedTest {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            System.out.println("lock block");
        }
        lockMethod();
    }

    private static synchronized void lockMethod() {
        System.out.println("lock method");
    }

}
