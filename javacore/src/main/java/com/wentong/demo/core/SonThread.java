package com.wentong.demo.core;

import java.util.concurrent.TimeUnit;

/**
 * 测试父线程结束后，子线程是否还继续运行
 */
public class SonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());

            Thread thread1 = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
            thread1.setName("子线程");
            thread1.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());

        });
        thread.setName("父线程");
        thread.start();

        TimeUnit.SECONDS.sleep(100);
    }

}
