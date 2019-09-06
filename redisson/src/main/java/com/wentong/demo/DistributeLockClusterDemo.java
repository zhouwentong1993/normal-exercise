package com.wentong.demo;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.Executors;

public class DistributeLockClusterDemo {

    public static void main(String[] args) {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useClusterServers()
                .addNodeAddress("http://127.0.0.1:7000")
                .addNodeAddress("http://127.0.0.1:7001")
                .addNodeAddress("http://127.0.0.1:7002")
                .addNodeAddress("http://127.0.0.1:7003")
                .addNodeAddress("http://127.0.0.1:7004")
                .addNodeAddress("http://127.0.0.1:7005");

        config.setExecutor(Executors.newFixedThreadPool(2000));
        // 指定分布式锁中未添加超时时间的情况，默认值
        config.setLockWatchdogTimeout(30000);

        RedissonClient redisson = Redisson.create(config);
        RLock testCluster = redisson.getLock("a");
        RedissonRedLock redissonRedLock = new RedissonRedLock(testCluster);
        redissonRedLock.lock();
        try {
            System.out.println("getLock");
        } finally {
            redissonRedLock.unlock();
        }
    }
}
