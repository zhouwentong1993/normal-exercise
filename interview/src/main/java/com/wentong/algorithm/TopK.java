package com.wentong.algorithm;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * TopK，通过优先队列实现
 *
 */
public class TopK {

    private static final int topKNum = 3;

    private static PriorityQueue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(100);
            System.out.println("add: " + num);
            add(num);
            System.out.println("topK: " + queue);
        }
    }

    private static int add(int num) {
        if (queue.size() < topKNum) {
            queue.offer(num);
            return num;
        } else {
            if (queue.peek() < num) {
                int min = queue.poll();
                queue.offer(num);
                return min;
            } else {
                return num;
            }
        }
    }

}
