package com.wentong.ratelimiter.limiter;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class FixedWindowLimiter implements Limiter {

    private static final String LIMIT_KEY = "fix_window_limiter:";
    private static final int LIMIT_COUNT = 100;
    private final RedisTemplate redisTemplate;

    public FixedWindowLimiter(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean acquire(String key) {
        int currentMinute = LocalDateTime.now().getMinute();
        String redisKey = LIMIT_KEY + key + ":" + currentMinute;
        Integer counter = (Integer) redisTemplate.opsForValue().get(redisKey);
        if (counter == null) {
            redisTemplate.opsForValue().increment(redisKey, 1);
            redisTemplate.expire(redisKey, 1, TimeUnit.MINUTES);
            return true;
        } else if (counter < LIMIT_COUNT) {
            redisTemplate.opsForValue().increment(redisKey, 1);
            return true;
        } else {
            return false;
        }
    }
}
