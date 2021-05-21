package com.wentong.demo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestConcurrentLinkedQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        System.out.println(queue.poll());
    }
}
