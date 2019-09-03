package com.wentong.demo;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LockDemo1 {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("http://127.0.0.1:6379");
        config.setExecutor(Executors.newFixedThreadPool(2000));
        // 指定分布式锁中未添加超时时间的情况，默认值
        config.setLockWatchdogTimeout(30000);

        RedissonClient redisson = Redisson.create(config);
        RLock testLock1 = redisson.getLock("testLock1");
        testLock1.lock();
        new Thread(() -> {
            RLock testLock11 = redisson.getLock("testLock1");
            testLock11.lock();
            try {

            } finally {
                testLock11.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(40);
        testLock1.unlock();
    }
}
