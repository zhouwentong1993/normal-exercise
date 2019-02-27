package com.wentong.demo;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 对优先级队列的测试，为 DelayQueue 做准备
 */
public class TestPriorityQueue {

    @Test
    public void testBasicUse() throws Exception{
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("b");
        queue.add("e");
        queue.add("d");
        queue.add("d");
        queue.add("a");
        queue.add("f");
        queue.add("c");
        for (int i = 0; i < 6; i++) {
            String peek = queue.peek();
            peek = null;
        }
        System.out.println(queue);
    }
}
