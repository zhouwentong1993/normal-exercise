package com.wentong.algorithm;

import java.util.LinkedList;

/**
 * LRU(Least recent use)
 */
public class LRUCache {

    private static final LinkedList<String> CACHE = new LinkedList<>();
    private static final int MAX_CACHE_SIZE = 1000;

    public static synchronized void add(String obj) {
        if (CACHE.contains(obj)) {
            CACHE.remove(obj);
        } else if (CACHE.size() >= MAX_CACHE_SIZE){
            int removeCount = CACHE.size() - MAX_CACHE_SIZE;
            for (int i = 0; i <= removeCount; i++) {
                CACHE.removeLast();
            }
        }
        CACHE.addFirst(obj);
    }
}
