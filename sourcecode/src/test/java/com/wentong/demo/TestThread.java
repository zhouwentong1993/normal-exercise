package com.wentong.demo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestThread {


    /**
     * 一个线程不能被 start 两次，问题：Thread 里面保存了一个变量，这个变量负责检验状态，但是没有找到它更新的地方
     */
    @Test
    public void testThreadStart() throws Exception{
        Thread thread = new Thread(() -> {
            System.out.println("child Thread");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.start();
        TimeUnit.SECONDS.sleep(5);
    }

}
