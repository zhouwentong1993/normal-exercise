package com.wentong.demo.concurrent;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                try {
                    System.out.println("Thread1 get lock1");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Thread1 get lock2");
                }
            }
        }
    }
    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                try {
                    System.out.println("Thread2 get lock2");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("Thread2 get lock1");
                }
            }
        }
    }


}
