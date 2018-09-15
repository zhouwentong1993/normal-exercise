package com.finup.demo;


import org.junit.Test;

import java.util.Random;

/**
 * 5. 有一个袋子里面装了黑白两种颜色的球若干个，现在每次从袋子里摸两个球，如果摸到的是相同颜色的球
 * ，那么将两个球丢弃并且重新放一个黑球进去，如果摸到的是不同颜色的球，则将黑球白球放回，
 * 问最后如果袋子里面存在哪些情况，并且详细说明过程，推导方法。如果将问题修改成摸到相同颜色的球直接丢弃不放回的话，
 * 这个时候整个过程会有哪些变化。
 */
public class BlackWhiteBall {

    // 1000 个白球，1000 个黑球。白球为 0，黑球为 1，抽完为 -1
    @Test
    public void testQ1() {
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
        for (int i = 0; i < 10; i++) {
            int randomNum1 = -1;
            int index1 = -1;
            while (randomNum1 == -1) {
                int index = getRandomNum();
                randomNum1 = arr[index];
                index1 = index;
            }

            int randomNum2 = -1;
            int index2 = -1;
            while (randomNum2 == -1) {
                int index = getRandomNum();
                randomNum2 = arr[index];
                index2 = index;
            }

            if (randomNum1 == 1) {
                if (randomNum1 == randomNum2) {
                    arr[index1] = -1;
                    arr[index2] = 1;
                } else {
                    arr[index1] = randomNum1;
                    arr[index2] = randomNum2;
                }
            } else if (randomNum1 == 0) {
                if (randomNum1 == randomNum2) {
                    arr[index1] = -1;
                    arr[index2] = 1;
                } else {
                    arr[index1] = randomNum1;
                    arr[index2] = randomNum2;
                }
            }
//            System.out.println("抓到：「" + ((randomNum1 == 1) ? "黑" : "白") + "，" + ((randomNum2 == 1) ? "黑" : "白") + "」");

        }

        int blackCount = 0;
        int whiteCount = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                whiteCount = whiteCount + 1;
            } else if (arr[i] == 1) {
                blackCount = blackCount + 1;
            }
        }

        System.out.println("white is :" + whiteCount);
        System.out.println("black is :" + blackCount);
    }

    private int getRandomNum() {
        Random random = new Random();
        return random.nextInt(2000);
    }

    @Test
    public void test() {
//        int[] arr = new int[10];
//        System.out.println(arr[10]);
    }
}
