package com.finup.demo.wheel;

import java.util.LinkedList;

public class ThreadPool {

    private LinkedList<Thread> pool = new LinkedList<>();

    private final int DEFAULT_CAPACITY = 100;
    private final int DEFAULT_INIT_SIZE = 10;
    private int size;
    private int capacity;

    public ThreadPool(int size) {
        if (size > DEFAULT_CAPACITY) {
            throw new IllegalArgumentException("size：" + size + "大于最大容量：" + DEFAULT_CAPACITY);
        }
        this.capacity = DEFAULT_CAPACITY;
        for (int i = 0; i < size; i++) {
            pool.addFirst(new Thread());
        }
        this.size = size;
    }

    

}
