package com.wentong.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LRU 升级版，因为上一个版本的 LRU 每次都需要遍历链表，时间复杂度较高，所以该版本通过 HashMap 记录位置
 */
public class LRUCache2 {

    private static final LinkedList<String> CACHE = new LinkedList<>();
    private static final int MAX_CACHE_SIZE = 1000;
    private static final Map<String, Integer> CACHE_INDEX = new HashMap<>();

    public static synchronized void add(String obj) {
        if (CACHE_INDEX.containsKey(obj)) {
            int index = CACHE_INDEX.get(obj);
            CACHE.remove(index);
            CACHE.addFirst(obj);
            CACHE_INDEX.remove(obj);
            CACHE_INDEX.forEach((key, sourceIndex) -> {
                if (!key.equals(obj)) {
                    CACHE_INDEX.put(key, sourceIndex + 1);
                }
            });
            CACHE_INDEX.put(obj, 0);
        } else if (CACHE.size() >= MAX_CACHE_SIZE) {
            int removeCount = CACHE.size() - MAX_CACHE_SIZE;
            for (int i = 0; i <= removeCount; i++) {
                String s = CACHE.removeLast();
                CACHE_INDEX.remove(s);
            }
            CACHE.addFirst(obj);
            CACHE_INDEX.forEach((key, sourceIndex) -> {
                if (!key.equals(obj)) {
                    CACHE_INDEX.put(key, sourceIndex + 1);
                }
            });
            CACHE_INDEX.put(obj, 0);
        }
    }

}
