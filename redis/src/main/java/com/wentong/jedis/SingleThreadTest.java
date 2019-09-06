package com.wentong.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;

public class SingleThreadTest {
    public static JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        //config.setMinIdle(10);
        config.setMaxWaitMillis(1000L);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        //config.testWhileIdle=true;
        //config.minEvictableIdleTimeMillis=30000;
        //config.timeBetweenEvictionRunsMillis=30000;

        pool = new JedisPool(config, "127.0.0.1", 6379);
    }

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1000);

        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            Jedis jedis = pool.getResource();
            threads[i] = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jedis.incr("test");
                jedis.close();
            });
            threads[i].start();
        }

        for (int i = 0; i < 1000; i++) {

        }


        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
