package com.wentong.ratelimiter.limiter;

public interface Limiter {

    boolean acquire(String key);

}
