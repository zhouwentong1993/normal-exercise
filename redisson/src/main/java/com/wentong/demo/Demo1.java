package com.wentong.demo;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("AlibabaThreadPoolCreation")
public class Demo1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);

    public static void main(String[] args) throws Exception {

        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("http://127.0.0.1:6379");
        config.setExecutor(Executors.newFixedThreadPool(2000));
        // 指定分布式锁中未添加超时时间的情况，默认值
        config.setLockWatchdogTimeout(30000);

        RedissonClient redisson = Redisson.create(config);
        RAtomicLong myLong = redisson.getAtomicLong("myLong");
//        System.out.println(myLong);
        boolean b = myLong.compareAndSet(1, 401);
//        System.out.println(b);
        RFuture<Boolean> booleanRFuture = myLong.compareAndSetAsync(3, 401);
        Boolean aBoolean = booleanRFuture.get();
//        System.out.println(aBoolean);
        RedissonReactiveClient reactive = Redisson.createReactive();
        RMap<Object, Object> mymap = redisson.getMap("mymap");

        RKeys keys = redisson.getKeys();
//        System.out.println(keys.count());
        Iterable<String> keys1 = keys.getKeys();

        RBitSet simpleBitset = redisson.getBitSet("simpleBitset");
        simpleBitset.set(0, true);
        simpleBitset.set(1812, false);
        System.out.println(simpleBitset.length());

        RRateLimiter myRateLimiter = redisson.getRateLimiter("myRateLimiter");
        myRateLimiter.trySetRate(RateType.OVERALL, 10, 1, RateIntervalUnit.SECONDS);

        RMap<Object, Object> anyMap = redisson.getMap("anyMap");
        anyMap.put("aaa", "aaa");
        anyMap.fastPut("bbb", "bbb");
        RMap<Object, Object> anyMap1 = redisson.getMap("anyMap");
        Object aaa = anyMap1.get("aaa");
        System.out.println("getMap: " + aaa);

        RMap<Object, Object> lockMap = redisson.getMap("lockMap");
        RLock lock1 = lockMap.getLock("lock1");
        lock1.unlock();
        lock1.lock();
        System.out.println("lock1 get Lock");
        try {
            new Thread(() -> {
                RMap<Object, Object> lockMap2 = redisson.getMap("lockMap");
                RLock lock2 = lockMap2.getLock("lock1");
                lock2.lock();
                try {
                    System.out.println("lock2 get lock!!!");
                } finally {
                    lock2.unlock();
                    System.out.println("lock2 release lock!!!");
                }
            }).start();
            TimeUnit.SECONDS.sleep(10);

        } finally {
            lock1.unlock();
            System.out.println("lock1 release lock!!!");
        }
        RBlockingQueue<String> blockingFairQueue = redisson.getBlockingQueue("delay_queue");

        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(blockingFairQueue);
        delayedQueue.offer("msg1", 10, TimeUnit.SECONDS);
        delayedQueue.offer("msg2", 1, TimeUnit.MINUTES);

        System.out.println(mymap.getName());
        System.out.println(redisson);
    }
}
