package com.wentong.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class TestLongAdder {

    public static void main(String[] args) throws Exception{
        atomicLong();
    }

    private static void atomicLong() throws InterruptedException {
        AtomicLong counter = new AtomicLong(0);
        AtomicBoolean stop = new AtomicBoolean(false);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                while (counter.get() <= 1000000 && !stop.get()) {
                    long l = counter.incrementAndGet();
                    System.out.println("Thread:" + Thread.currentThread().getName() + ",count:" + l);
                }
                stop.set(true);
                System.out.println(Thread.currentThread().getName() + "stop!!");
                System.out.println("count: " + counter.get());
            });
            thread.setName("Thread " + i);
            thread.start();
        }
        TimeUnit.SECONDS.sleep(10);
    }

    private static void longAdder() {
        LongAdder longAdder = new LongAdder();

    }

}
