package com.wentong.concurrent;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 各种限流方式
 */
public class TrafficLimit {

    public static void main(String[] args) throws Exception {
        RateLimiter rateLimiter = RateLimiter.create(5);
        System.out.println(rateLimiter.acquire(10));
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());

    }

    private static void slidingWindowLimit() throws Exception {
        LoadingCache<Long, AtomicLong> loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long seconds) {
                        return new AtomicLong(0);
                    }
                });
        long limit = 800;
        printCacheInfo(loadingCache);
        while (true) {
            long currentSeconds = System.currentTimeMillis() / 1000;
            long l = loadingCache.get(currentSeconds).incrementAndGet();
            System.out.println(l);
            if (l > limit) {
                System.out.println("限流了");
                continue;
            } else {
                System.out.println("没限流");
                TimeUnit.MILLISECONDS.sleep(1);
            }
        }
    }

    private static void printCacheInfo(LoadingCache<Long, AtomicLong> loadingCache) {
        new Thread(() -> {
            while (true) {
                loadingCache.asMap().forEach((key, value) -> {
                    System.out.println(key + ":" + value);
                });
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }

}
