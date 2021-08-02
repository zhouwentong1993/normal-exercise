package com.wentong.caffeine.demo;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.wentong.caffeine.demo.Person.createExpensiveGraph;

public class AddCache {
    public static void main(String[] args) throws Exception {
//        loadCacheManual();
        loadCacheAutomatic();
    }

    /**
     * 自动将数据加载进内存中，同步操作
     */
    private static void loadCacheAutomatic() {
        LoadingCache<String, Person> cache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(Person::createExpensiveGraph);
        Person p = cache.get("李四");
        System.out.println(p);

        Map<String, Person> map = cache.getAll(List.of("张三"));
        System.out.println(map);
    }

    /**
     * 手动加载缓存，同步操作
     */
    private static void loadCacheManual() throws InterruptedException {
        Cache<String, Person> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(10_000)
                .build();

        // 查找一个缓存元素， 没有查找到的时候返回null
        String name = "Jack";
        Person someone = cache.getIfPresent(name);
        // 查找缓存，如果缓存不存在则生成缓存元素,  如果无法生成则返回null
        someone = cache.get(name, k -> createExpensiveGraph(name));
        // 添加或者更新一个缓存元素
        cache.put(name, someone);
        System.out.println(cache.getIfPresent(name));
        TimeUnit.SECONDS.sleep(11);
        System.out.println(cache.getIfPresent(name));
        // 移除一个缓存元素
        cache.invalidate(name);
    }

}
