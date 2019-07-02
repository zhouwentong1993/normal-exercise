package com.wentong.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    public void set(String key,String value) {
        LOGGER.info("key:{},value:{}", key, value);
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        LOGGER.info("key:{}", key);
        return (String) redisTemplate.opsForValue().get(key);
    }
}
