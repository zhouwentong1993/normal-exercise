package com.wentong.demo.volatiledemo;

import java.util.concurrent.CountDownLatch;

public class ReorderTest {

    int a = 0;
    int b = 0;

    public void set() {
        a = 1;
        b = 1;
    }

    public boolean loop() {
        while (b == 0) {
            continue;
        }
        if (a == 1) {
            System.out.println("i'm here");
            return true;
        } else {
            System.out.println("what's wrong");
            return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100_0000; i++) {
            ReorderTest test = new ReorderTest();
            CountDownLatch latch = new CountDownLatch(2);
            Thread t1 = new Thread(() -> {
                try {
                    latch.await();
                    test.set();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t1");
            t1.start();
            int finalI = i;
            Thread t2 = new Thread(() -> {
                try {
                    latch.await();
                    boolean result = test.loop();
                    if (!result) {
                        System.err.println("After loop " + finalI + " times, reorder happens!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t2");
            t2.start();
            latch.countDown();
        }
    }

}
