package com.wentong.demo.wheel;

public interface Queue<T> {

    boolean put(T t);

    T pop();

    T peek();

    int size();

    int capacity();
}
