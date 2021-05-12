package com.wentong.demo.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

// 通过 LinkedHashMap 实现 LRU CACHE。
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(10);
        for (int i = 0; i < 11; i++) {
            cache.put(i, i);
        }
        System.out.println(cache);
    }
}
