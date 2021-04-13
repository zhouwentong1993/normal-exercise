package com.wentong.demo.core;

import java.util.Random;

public class VariableGet {
    private static int counter = randomValue();
    private static int randomValue() {
        return new Random().nextInt(1000);
    }

    // 值是相同的，变量只会初始化一次。
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(counter);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(randomValue());
        }
    }
}
