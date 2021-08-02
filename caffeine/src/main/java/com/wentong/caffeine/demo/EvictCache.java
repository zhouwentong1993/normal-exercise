package com.wentong.caffeine.demo;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试驱逐缓存
 * Caffeine 提供了三种缓存驱逐策略，分别是：基于 size、基于时间和基于引用的。
 */
public class EvictCache {

    // https://stackoverflow.com/questions/61380679/caffeine-eviction-by-size-seems-to-not-work
    // 基于 entry size 就是 判断 entry 的个数，超过这些个数，则应当被驱逐。
    @Test
    public void testEntryCountLimit() {
        LoadingCache<String, Person> cache = Caffeine.newBuilder()
                .maximumSize(10).build(Person::createExpensiveGraph);
        for (int i = 0; i < 111; i++) {
            cache.get(String.valueOf(i));
        }
        System.out.println(cache.estimatedSize());
        System.out.println(cache.get("0"));
        List<Person> collect = new ArrayList<>(cache.asMap().values());
        System.out.println(collect);

    }

    // 但是基于权重我就不知道是什么意思了
    @Test
    public void testEntryWeightLimit() {
        LoadingCache<String, String> cache = Caffeine.newBuilder().maximumWeight(1).weigher((String key, String value) -> key.length()).build(key -> key);
        for (int i = 0; i < 100; i++) {
            cache.get(String.valueOf(i));
        }
        ArrayList<String> list = new ArrayList<>(cache.asMap().values());
        System.out.println(list);
    }
}
