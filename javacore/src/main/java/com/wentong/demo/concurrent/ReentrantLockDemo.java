package com.wentong.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            new Thread(() -> {
                lock.lock();
                System.out.println("hahahah");
            }, "lock thread").start();
            TimeUnit.SECONDS.sleep(10);
        } finally {
//            lock.unlock();
        }
    }
}
