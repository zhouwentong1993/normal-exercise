package com.wentong.performance;

import com.google.common.base.Stopwatch;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CountDownLatch;

public class PureJedisMultiThreadTest {

    public static void main(String[] args) throws Exception {
        int threadCount = Runtime.getRuntime().availableProcessors();
        int requestCount = 100000;

        Stopwatch stopwatch = Stopwatch.createStarted();
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try (Jedis jedis = new Jedis()) {
                    for (int j = 0; j < requestCount / threadCount; j++) {
                        jedis.get("a");
                    }
                }
                System.out.println("Thread: " + Thread.currentThread().getName() + " executed finished!");
                latch.countDown();
            }).start();

        }

        latch.await();
        stopwatch.stop();
        System.out.println(stopwatch);
    }

}
