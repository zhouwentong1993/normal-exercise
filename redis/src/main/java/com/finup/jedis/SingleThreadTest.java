package com.finup.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
        Thread[] threads = new Thread[2000];
        for (int i = 0; i < 1000; i++) {
            Jedis resource = pool.getResource();
            threads[i] = new Thread(() -> {
                resource.incr("testIncr1");
            });
            threads[i].start();
            resource.close();
        }

//        for (int i = 1000; i < 2000; i++) {
//            threads[i] = new Thread(() -> {
//                Long current = Long.valueOf(jedis.get("testIncr"));
//                current = current + 1;
//                jedis.set("testIncr", String.valueOf(current));
//            });
//            threads[i].start();
//        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
//        System.out.println(jedis.get("testIncr"));
//        jedis.close();
    }
}
