package com.wentong.performance;

import com.google.common.base.Stopwatch;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;

public class JedisPoolMultiThreadTest {

    public static void main(String[] args) throws Exception {
        int threadCount = Runtime.getRuntime().availableProcessors();
        int requestCount = 1000000;

        CountDownLatch latch = new CountDownLatch(threadCount);

        Stopwatch stopwatch = Stopwatch.createStarted();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        // redisHost为实例的IP， redisPort 为实例端口，redisPassword 为实例的密码，timeout 既是连接超时又是读写超时
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1");
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                for (int j = 0; j < requestCount / threadCount; j++) {
                    try (Jedis jedis = jedisPool.getResource()) {
                        //具体的命令
                        jedis.get("a");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        stopwatch.stop();
        System.out.println(stopwatch);
    }

}
