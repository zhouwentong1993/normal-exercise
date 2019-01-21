package com.wentong.demo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class  TestAQS {

    @Test
    public void testCondition() throws Exception {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        new Thread(() -> {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(2);
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        try {
            System.out.println("condition wait start");
            condition.await();
            System.out.println("condition wait end");
        } finally {
            lock.unlock();
        }

        lock.lock();
        try {
            System.out.println("condition signal start");
            condition.signal();
            System.out.println("condition signal end");
        }finally {
            lock.unlock();
        }
    }


    /**
     * 只有一个线程能获取到锁，由于没有使用 CountDownLatch，所以通常是 Thread0 获取
     * 其他线程在 AQS 的队列按照顺序待着，没过 10s，就会按照顺序获取锁。
     * @throws Exception
     */
    @Test
    public void testTwoThreadInAQS() throws Exception{
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "get Lock");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.setName("thread" + i);
            thread.start();
        }
        TimeUnit.SECONDS.sleep(100);
    }

}
