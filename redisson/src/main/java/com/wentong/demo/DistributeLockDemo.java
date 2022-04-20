package com.wentong.demo;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DistributeLockDemo {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setExecutor(Executors.newFixedThreadPool(2000));
        // 指定分布式锁中未添加超时时间的情况，默认值
        config.setLockWatchdogTimeout(30000);

        RedissonClient redisson = Redisson.create(config);
        RLock lock1 = redisson.getLock("lock1");
        RLock lock2 = redisson.getLock("lock2");
        RLock lock3 = redisson.getLock("lock3");
        RedissonRedLock redissonRedLock = new RedissonRedLock(lock1, lock2, lock3);
        redissonRedLock.lock();
        try {
            System.out.println("lock1 lock2 lock3 locks all");
            new Thread(() -> {
                RLock lock11 = redisson.getLock("lock1");
                lock11.lock();
                try {
                    System.out.println("lock11 get lock");
                } finally {
                    lock11.unlock();
                    System.out.println("lock11 release lock");
                }
            }).start();
            TimeUnit.SECONDS.sleep(10);
        } finally {
            redissonRedLock.unlock();
            System.out.println("lock1 lock2 lock3 unlocks all");
        }

    }
}
