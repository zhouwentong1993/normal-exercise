package com.wentong.demo;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.Executors;

public class DelayedQueueConsumer {
    public static void main(String[] args) throws Exception{
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("http://127.0.0.1:6379");
        config.setExecutor(Executors.newFixedThreadPool(2000));
        // 指定分布式锁中未添加超时时间的情况，默认值
        config.setLockWatchdogTimeout(30000);

        RedissonClient redisson = Redisson.create(config);
        RBlockingQueue<String> blockingFairQueue = redisson.getBlockingQueue("delay_queue");

        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(blockingFairQueue);
        while (true) {
            String take = blockingFairQueue.take();
            System.out.println(take);
        }
    }
}
