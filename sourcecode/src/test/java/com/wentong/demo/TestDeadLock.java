package com.wentong.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 ReentrantLock 造成的死锁能否被 Java 自带工具检测出来
 * 能被检测出来，可能是 jconsole 做了一些特殊的事情
 */
public class TestDeadLock {

    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            lock1.lock();
            sleep1s();
            lock2.lock();
        }).start();

        new Thread(() -> {
            lock2.lock();
            sleep1s();
            lock1.lock();
        }).start();

        TimeUnit.SECONDS.sleep(100);
    }


    public static void sleep1s() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
