package com.wentong.demo;

import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedLockDemo1 {

    public static void main(String[] args) throws Exception {
        // connects to 127.0.0.1:6379 by default
        RedissonClient client1 = Redisson.create();
        RedissonClient client2 = Redisson.create();

        RLock lock1 = client1.getLock("lock1");
        RLock lock2 = client1.getLock("lock2");
        RLock lock3 = client2.getLock("lock3");

        Thread t1 = new Thread() {
            public void run() {
                lock3.lock();
            }

            ;
        };
        t1.start();
        t1.join();

        Thread t = new Thread() {
            public void run() {
                RedissonMultiLock lock = new RedissonRedLock(lock1, lock2, lock3);
                lock.lock();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                lock.unlock();
            }

        };
        t.start();
        t.join(1000);

        lock3.forceUnlock();

        RedissonMultiLock lock = new RedissonRedLock(lock1, lock2, lock3);
        lock.lock();
        lock.unlock();

        client1.shutdown();
        client2.shutdown();
    }

}
