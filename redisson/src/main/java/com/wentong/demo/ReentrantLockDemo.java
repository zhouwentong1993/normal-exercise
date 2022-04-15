package com.wentong.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        lock.lock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("thread:" + Thread.currentThread().getName() + " get lock.");
                } finally {
                    lock.unlock();
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } finally {
            lock.unlock();
        }
    }

}
