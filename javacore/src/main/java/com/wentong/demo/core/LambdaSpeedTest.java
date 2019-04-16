package com.wentong.demo.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * lambda 的性能测试
 */
public class LambdaSpeedTest {
    static List<Integer> list;

    static {
        list = new ArrayList<>(1000000);
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            list.add(random.nextInt(1000000));
        }


    }

    public static void main(String[] args) {
        normalFor();
        lambdaFor();
    }

    private static void normalFor() {
        long start = System.nanoTime();
        int max = 0;
        for (Integer integer : list) {
            max = Math.max(max, integer);
        }
        System.out.println("normalFor max:" + max);
        System.out.println("time is :" + (System.nanoTime() - start));
    }

    private static void lambdaFor() {
        long start = System.nanoTime();
        Integer max = list.stream().reduce(0, (a, b) -> Math.max(a, b));
        System.out.println("lambdaFor max:" + max);
        System.out.println("time is :" + (System.nanoTime() - start));
    }


}
