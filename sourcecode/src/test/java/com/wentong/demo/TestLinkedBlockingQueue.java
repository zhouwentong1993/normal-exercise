package com.wentong.demo;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

}
