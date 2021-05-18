package com.wentong.demo.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackToQueue {

    private static Deque<String> stack1 = new ArrayDeque<>();
    private static Deque<String> stack2 = new ArrayDeque<>();

    public static void push(String element) {
        stack1.push(element);
    }

    public static String pop() {
        while (!stack1.isEmpty()) {
            String pop = stack1.pop();
            stack2.push(pop);
        }
        return stack2.pop();
    }

}
