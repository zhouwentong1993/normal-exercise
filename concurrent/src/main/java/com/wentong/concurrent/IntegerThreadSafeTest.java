package com.wentong.concurrent;

import org.junit.Test;

public class IntegerThreadSafeTest {
    @Test
    public void testValueOf() throws Exception {
        Thread thread1 = new Thread(() -> {
            while (true) {
                System.out.println(Integer.valueOf("12"));
            }
        });
        thread1.setName("thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (true) {
                System.out.println(Integer.valueOf("12"));
            }
        });
        thread2.setName("thread2");
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
