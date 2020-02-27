package com.wentong.study;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeWheel {

    public static void main(String[] args) throws Exception {
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
        hashedWheelTimer.newTimeout(new MyTimerTask(), 10, TimeUnit.SECONDS);
        printTime();
        TimeUnit.SECONDS.sleep(1000);
    }

    private static void printTime() {
        AtomicInteger i = new AtomicInteger(1);
        new Thread(() -> {
            for (; ; ) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i.getAndIncrement());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class MyTimerTask implements TimerTask {

        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println("haha");
        }
    }

}
