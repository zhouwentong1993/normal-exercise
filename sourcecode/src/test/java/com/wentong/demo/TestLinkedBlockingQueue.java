package com.wentong.demo;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLinkedBlockingQueue {

    // LinkedBlockQueue 的 put 方法是阻塞的，如果没有消费，会一直阻塞
    @Test
    public void testPut() throws Exception {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        for (int i = 0; i < 12; i++) {
            queue.put(String.valueOf(i));
        }
        System.out.println(queue);
    }

    @Test
    public void testAtomicInteger() {
        AtomicInteger count = new AtomicInteger();
        System.out.println(count.getAndDecrement());
        System.out.println(count);

    }

    @Test
    public void testPoll() throws Exception {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
        String poll = queue.poll(10, TimeUnit.SECONDS);
        System.out.println(poll);
        System.out.println(queue.size());
    }

    @Test
    public void testOffer() throws Exception {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        for (int i = 0; i < 13; i++) {
            queue.offer(String.valueOf(i));
        }
        System.out.println(queue);
    }

    @Test
    public void testCurrentPutAndOffer() throws Exception {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Thread thread = new Thread(() -> {
            for (; ; ) {
                try {
                    queue.put(String.valueOf(121111));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        queue.offer(String.valueOf(123));

        System.out.println(queue);
    }

    Lock lock = new ReentrantLock();

    @Test
    public void testReentrantLock() throws Exception {
        new Thread(this::method1).start();
        new Thread(this::method2).start();
        TimeUnit.SECONDS.sleep(100);
    }

    private void method1() {
        try {
            lock.lock();
            System.out.println("TestLinkedBlockingQueue.method1");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    private void method2() {
        try {
            lock.lock();
            System.out.println("TestLinkedBlockingQueue.method2");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    @Test
    public void testCondition() throws Exception {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        new Thread(() -> {
            TimeUnit timeUnit = TimeUnit.SECONDS;
            long nanos = timeUnit.toNanos(10);
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(10);
                System.out.println("signal");
                // signal 为什么要设计成只允许当前线程去 signal 呢？？？
                condition1.signal();
                long l = condition1.awaitNanos(nanos);
                System.out.println(l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        TimeUnit.SECONDS.sleep(10);

//        condition1.signal();

    }


}
