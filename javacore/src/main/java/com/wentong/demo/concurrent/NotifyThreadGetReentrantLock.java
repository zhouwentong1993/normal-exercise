package com.wentong.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本代码测试的是当线程被 await 再被 signal 后，在争夺锁的队列时是怎么样的。
 * 结论：当被 notify 后，线程会最后争夺到锁。
 * ReentrantLock 和 synchronized 的方式是一样的。
 */
public class NotifyThreadGetReentrantLock {

    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        AtomicBoolean flag = new AtomicBoolean(false);
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    lock.lock();
                    try {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            condition.signal();
                            flag.set(true);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName());
                    } finally {
                        lock.unlock();
                    }
                }, "Thread:" + i).start();
                TimeUnit.MICROSECONDS.sleep(10);
            }
            while (!flag.get()) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }

    }

}
