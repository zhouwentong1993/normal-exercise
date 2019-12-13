package com.wentong.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LockThings {

    private static AtomicInteger serviceNum = new AtomicInteger(0);
    private static AtomicInteger ticketNum = new AtomicInteger(0);

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


    // 带票据的加锁
    public static void ticketSpinLock() {
        // 每次获取一个票据，所有线程都可以。
        int ticketNow = ticketNum.getAndIncrement();
        // 但是只有一个线程能跳出 while 循环
        while (serviceNum.get() != ticketNow) {

        }
    }

    // 带票据的解锁
    public static void ticketSpinUnLock(int ticket) {
        // 这里要将 ticket 置为下一个，这样下一个 ticket 就能获取到锁了。
        serviceNum.compareAndSet(ticket, ticket + 1);
    }




}
