package com.wentong.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantAndCas {

    private static Lock lock = new ReentrantLock();

    private static int count = 0;

    private static void incr() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        long l = System.nanoTime();

        int nThreads = 100;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();
                    for (int j = 0; j < 100000; j++) {
                        incr();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }
        startLatch.countDown();
        endLatch.await();
        System.out.println("count is:" + count);
        System.out.println("time is: " + (System.nanoTime() - l));
    }
}
