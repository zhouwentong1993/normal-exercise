package com.wentong.di.object;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class RateLimiter {
    private RedisCounter redisCounter;
}
