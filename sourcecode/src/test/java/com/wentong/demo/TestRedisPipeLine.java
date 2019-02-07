package com.wentong.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.TimeUnit;

public class TestRedisPipeLine {

    public static JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10000);
        //config.setMinIdle(10);
        config.setMaxWaitMillis(10000L);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        //config.testWhileIdle=true;
        //config.minEvictableIdleTimeMillis=30000;
        //config.timeBetweenEvictionRunsMillis=30000;

        pool = new JedisPool(config, "127.0.0.1", 6379);
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Jedis jedis = pool.getResource();
                for (int j = 0; j < 100; j++) {
                    jedis.incr("tts");
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(1000);
    }
}
