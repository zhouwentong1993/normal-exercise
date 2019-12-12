package com.wentong.concurrent;

import java.util.concurrent.atomic.AtomicReference;

public class LockThings {

    private static AtomicReference<Thread> owner = new AtomicReference<>();

    public static void main(String[] args) {

    }


    // 自旋加锁
    public static void spinLock() {
        Thread currentThread = Thread.currentThread();
        while (!owner.compareAndSet(null, currentThread)) {

        }
    }

    // 自旋解锁
    public static void spinUnLock() {
        owner.compareAndSet(Thread.currentThread(), null);
    }




}
