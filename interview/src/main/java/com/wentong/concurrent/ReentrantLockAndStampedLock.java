package com.wentong.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock、ReentrantReadWriteLock、StampedLock 三者 demo
 */
public class ReentrantLockAndStampedLock {

    private static double x = 1;
    private static double y = 1;

    public static void main(String[] args) throws Exception {
        reentrantLockTest();
    }

    public static void reentrantLockTest() {
        long start = System.currentTimeMillis();
        Lock lock = new ReentrantLock();
        Random random = new Random();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 500; i++) {
            Thread thread = new Thread(() -> reentrantLockWrite(lock, countDownLatch));
            thread.setName("write Thread:" + i);
            thread.start();
        }
        for (int i = 0; i < 2500; i++) {
            Thread thread = new Thread(() -> reentrantLockRead(lock, countDownLatch));
            thread.setName("read Thread:" + i);
            thread.start();
        }

        System.out.println("ready all");
        countDownLatch.countDown();
        System.out.println("total = " + (System.currentTimeMillis() - start));
    }

    private static double reentrantLockRead(Lock lock, CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
//            sleepSeconds(1);
            System.out.println("thread:" + Thread.currentThread().getName() + " get read lock");
            return Math.sqrt(x * x + y * y);
        } finally {
            lock.unlock();
        }
    }

    private static void reentrantLockWrite(Lock lock, CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
//            sleepSeconds(1);
            System.out.println("thread:" + Thread.currentThread().getName() + " get write lock");
            x = x + 1;
            y = y + 1;
        } finally {
            lock.unlock();
        }
    }


    public static void reentrantReadWriteLockTest() {

    }

    public static void stampedLockTest() {

    }

    private static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
