package com.wentong.demo;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch {

    @Test
    public void testCountDown()throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "start");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "stop");
            });
            thread.setName("Thread:" + i);
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            countDownLatch.countDown();
        }

    }
}
