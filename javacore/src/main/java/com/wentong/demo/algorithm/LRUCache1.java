package com.wentong.demo.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

// 通过 set 和 linkedList 实现 lru
public class LRUCache1 {
    private Set<String> keys = new HashSet<>();
    private LinkedList<String> list = new LinkedList<>();

    private int capacity;

    public LRUCache1(int capacity) {
        this.capacity = capacity;
    }

    public void put(String entry) {
        Objects.requireNonNull(entry);
        if (keys.contains(entry)) {
            list.remove(entry);
            list.addFirst(entry);
        } else {
            if (list.size() >= capacity) {
                String target = list.removeLast();
                keys.remove(target);
            }
            list.addFirst(entry);
            keys.add(entry);
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        LRUCache1 cache = new LRUCache1(10);
        for (int i = 0; i < 11; i++) {
            cache.put(String.valueOf(i));
        }
        System.out.println(cache);
    }
}
