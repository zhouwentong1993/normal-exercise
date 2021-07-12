package com.wentong.demo.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundBuffer {

    public static void main(String[] args) throws Exception {
        BoundBuffer boundBuffer = new BoundBuffer();
        new Thread(() -> {
            for (int i = 0; i < 1100; i++) {
                boundBuffer.put(i);
                System.out.println("putThread put:" + i + "into arr");
            }
        }, "putThread").start();
        TimeUnit.SECONDS.sleep(3);

        new Thread(() -> {
            for (int i = 0; i < 1100; i++) {
                Object take = boundBuffer.take();
                System.out.println("takeThread take:" + take + "from arr");
            }
        }, "takeThread").start();
    }

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private static final int MAX_SIZE = 1024;
    private final Object[] arr = new Object[MAX_SIZE];
    private int putIndex = 0;
    private int takeIndex = 0;
    private int count = 0;

    public void put(Object obj) {
        lock.lock();
        try {
            while (count == MAX_SIZE) {
                notFull.await();
            }
            arr[putIndex] = obj;
            putIndex++;
            count++;
            if (putIndex == MAX_SIZE) {
                putIndex = 0;
            }
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object obj = arr[takeIndex];
            takeIndex++;
            count--;
            if (takeIndex == MAX_SIZE) {
                takeIndex = 0;
            }
            notFull.signal();
            return obj;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }

}
