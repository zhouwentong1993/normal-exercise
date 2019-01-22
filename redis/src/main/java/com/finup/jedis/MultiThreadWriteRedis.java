package com.finup.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MultiThreadWriteRedis {
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


}
