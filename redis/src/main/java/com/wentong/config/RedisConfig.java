package com.wentong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zhouwentong on 2018/7/25.
 */
@Configuration
public class RedisConfig {

    private int timeout = 2000;

    @Bean(name = "jedis.pool")
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config) {
        return new JedisPool(config, "localhost", 6379, timeout, null, 0);
    }

    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(100);
//        config.setMaxIdle(10);
//        config.setMinIdle(10);
//
//        config.setMaxWaitMillis(100000);
//        config.setJmxEnabled(true);
//        config.setTestOnBorrow(true);
//        config.setTestOnReturn(true);
//        config.setTestWhileIdle(true);
//        config.setTestOnCreate(true);
//        config.setSoftMinEvictableIdleTimeMillis(180 * 1000L);
        return config;
    }


}