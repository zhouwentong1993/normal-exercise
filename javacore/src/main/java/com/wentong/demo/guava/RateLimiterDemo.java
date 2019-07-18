package com.wentong.demo.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 使用 Guava 提供的 RateLimiter 简单的限流
 * https://segmentfault.com/a/1190000016182737
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1);
        for (int i = 1; i < 10; i = i + 2) {
            // 可以提前消费，但是要还的。
            double acquireTime = rateLimiter.acquire(i);
            System.out.println("i = " + i + ",acquireTime = " + acquireTime);
        }
    }

}
