package com.wentong.demo;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrant {

    @Test
    public void testLock() throws Exception {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                sleep();
            } finally {
                lock.unlock();
            }
        }).start();
    }

    @SneakyThrows
    private void sleep() {
        TimeUnit.SECONDS.sleep(1);
    }

}
