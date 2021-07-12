package com.wentong.demo.concurrency;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {

    @Test
    public void testReentrantLock1() throws Exception {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(1000);
        } finally {
            lock.unlock();
        }
        condition.await();
    }
}
