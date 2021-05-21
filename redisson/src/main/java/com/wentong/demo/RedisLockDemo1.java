package com.wentong.demo;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RedisLockDemo1 {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("http://127.0.0.1:6379");
        config.setExecutor(Executors.newFixedThreadPool(2000));
        // 指定分布式锁中未添加超时时间的情况，默认值
        config.setLockWatchdogTimeout(32000);

        RedissonClient redisson = Redisson.create(config);
        RLock testLock1 = redisson.getLock("testLock1");
        testLock1.lock();
        counter();


        new Thread(() -> {
            RLock testLock11 = redisson.getLock("testLock1");
            testLock11.lock();
            try {
                System.out.println("sub thread get lock");
            } finally {
                testLock11.unlock();
            }
        }, "sub").start();
        try {
            testLock1.lock();
            testLock1.lock();

            System.out.println("main thread get lock");
            TimeUnit.SECONDS.sleep(20);
        } finally {
            testLock1.unlock();
        }

    }

    private static void counter() {
        AtomicInteger counter = new AtomicInteger();

        new Thread(() -> {
            for (; ; ) {
                counter.getAndIncrement();
                System.out.println(counter.get());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
