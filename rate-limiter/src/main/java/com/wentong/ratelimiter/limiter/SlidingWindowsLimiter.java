package com.wentong.ratelimiter.limiter;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SlidingWindowsLimiter implements Limiter {

    private static final String LIMIT_KEY = "sliding_window_limiter:";
    private static final int LIMIT_COUNT = 10;
    private final RedisTemplate redisTemplate;

    public SlidingWindowsLimiter(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean acquire(String key) {
        long now = System.currentTimeMillis();
        Long count = redisTemplate.opsForZSet().count(LIMIT_KEY, (double) now - 60 * 1000, now);
        if (count == null || count < LIMIT_COUNT) {
            redisTemplate.opsForZSet().add(LIMIT_KEY, key + now, now);
            redisTemplate.expire(LIMIT_KEY, 2, TimeUnit.MINUTES);
            return true;
        } else {
            return false;
        }
    }

}
