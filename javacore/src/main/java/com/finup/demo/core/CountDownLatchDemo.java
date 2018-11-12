package com.finup.demo.core;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

    @Test
    public void testNormalThread() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
            });
            thread.setName("Thread" + i);
            thread.start();
        }
    }

    @Test
    public void testCountDownLatchThread() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setName("Thread" + i);
            thread.start();
        }


        countDownLatch.countDown();

        TimeUnit.SECONDS.sleep(2);
    }

}
