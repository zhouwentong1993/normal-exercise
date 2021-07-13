package com.wentong.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 本代码测试的是当线程被 wait 再被 notify 后，在争夺锁的队列时是怎么样的。
 * 结论：当被 notify 后，线程会最后争夺到锁。
 */
public class NotifyThreadGetSynchronizedLock {

    public static void main(String[] args) throws Exception {
        Object locker = new Object();
        AtomicBoolean flag = new AtomicBoolean(false);
        synchronized (locker) {
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    synchronized (locker) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            locker.notifyAll();
                            flag.set(true);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName());
                    }
                }, "Thread:" + i).start();
                TimeUnit.MICROSECONDS.sleep(10);
            }
            while (!flag.get()) {
                locker.wait();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

}
