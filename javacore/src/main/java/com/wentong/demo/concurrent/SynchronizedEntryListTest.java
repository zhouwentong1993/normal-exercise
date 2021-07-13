package com.wentong.demo.concurrent;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 本文为了测试 Synchronize 的 entrylist 在获取锁是是按照什么顺序来的。
 * 在 ReentrantLock 中，队列是按照先进先出的顺序来的。
 * 在这个程序中，我们按照从 0-9 的顺序将线程依次创建，然后线程都被阻塞在 obj 的锁上
 * 当主线程释放锁之后，各个线程依次拿到锁，但是被调度的顺序是跟进入的顺序相反。即进入是 0-9，出来是 9-0
 */
@Slf4j(topic = "test")
public class SynchronizedEntryListTest {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        synchronized (obj) {
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    synchronized (obj) {
                        System.out.println(Thread.currentThread().getName());
                    }
                }, "Thread:" + i).start();
                TimeUnit.MICROSECONDS.sleep(10);
            }
        }
    }

}
