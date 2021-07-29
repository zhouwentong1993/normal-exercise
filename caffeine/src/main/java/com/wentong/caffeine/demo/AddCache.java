package com.wentong.caffeine.demo;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class AddCache {
    public static void main(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();

        // 查找一个缓存元素， 没有查找到的时候返回null
        String key = "abc";
        String graph = cache.getIfPresent(key);
        // 查找缓存，如果缓存不存在则生成缓存元素,  如果无法生成则返回null
        graph = cache.get(key, k -> createExpensiveGraph(key));
        // 添加或者更新一个缓存元素
        cache.put(key, graph);
        // 移除一个缓存元素
        cache.invalidate(key);
    }

    private static String createExpensiveGraph(String key) {
        return key;
    }
}
