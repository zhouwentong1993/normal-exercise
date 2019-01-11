package com.finup.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep 过程中不会释放锁，而 wait 会。
 */
public class SleepThread {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("locked");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("unlock");
                reentrantLock.unlock();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        reentrantLock.lock();
        try {
            System.out.println("main locked");
        } finally {
            System.out.println("main unlock");
            reentrantLock.unlock();
        }
    }
}
