package com.finup.demo.concurrent;

public class WaitStateDemo {
    private static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        synchronized (lock) {
            while (true) {
                lock.wait();
            }
        }
    }
}
