package com.wentong.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1000);
        System.out.println(atomicInteger.get());
    }

}
