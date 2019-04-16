package com.wentong.demo.wheel;

public class BlockingQueue<T> implements Queue<T> {

    private DefaultQueue defaultQueue;

    private int size;



    @Override
    public boolean put(T t) {
        return false;
    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }
}
