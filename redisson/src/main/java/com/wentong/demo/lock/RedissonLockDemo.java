package com.wentong.demo.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RedissonLockDemo {

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setExecutor(Executors.newFixedThreadPool(2000));
        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("anyLock");
        // 最常见的使用方法
        lock.lock();
        TimeUnit.SECONDS.sleep(100);
        System.out.println("Thread: " + Thread.currentThread().getName() + " get lock.");
        new Thread(() -> {
            lock.lock(3, TimeUnit.SECONDS);
            System.out.println("Thread: " + Thread.currentThread().getName() + " get lock.");
            lock.unlock();
            System.out.println("Thread: " + Thread.currentThread().getName() + " release lock.");
        }).start();
        TimeUnit.SECONDS.sleep(1);
        lock.unlock();
        System.out.println("Thread: " + Thread.currentThread().getName() + " release lock.");
        TimeUnit.SECONDS.sleep(10);
    }

}
