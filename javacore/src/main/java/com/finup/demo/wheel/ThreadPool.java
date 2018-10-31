package com.finup.demo.wheel;

import java.util.LinkedList;

public class ThreadPool {

    private final int DEFAULT_MAX_SIZE = 100;
    private final int DEFAULT_INIT_SIZE = 10;
    private int size;

    public ThreadPool(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {

        }
    }

    private LinkedList<Thread> pool = new LinkedList<>();


}
