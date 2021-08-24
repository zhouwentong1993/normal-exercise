package com.wentong.caffeine.demo;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RefreshCache {

    @Test
    public void testRefresh() throws Exception {
        LoadingCache<String, String> cache = Caffeine.newBuilder().refreshAfterWrite(3, TimeUnit.SECONDS).build(key -> key + "-refresh");
        cache.put("a", "a");
        System.out.println(cache.asMap());
        TimeUnit.SECONDS.sleep(4);
        System.out.println(cache.asMap());
        System.out.println(cache.asMap());
        System.out.println(cache.asMap());
        System.out.println(cache.asMap());
    }
}
