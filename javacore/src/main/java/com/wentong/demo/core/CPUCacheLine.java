package com.wentong.demo.core;

public class CPUCacheLine {

    static final int LINE_NUM = 1024;
    static final int COLUMN_NUM = 1024;

    public static void main(String[] args) {
        inCacheLine();
        notInCacheLine();
    }

    /*
total time is 13153698
total time is 13226237
total time is 14454714
total time is 18054195
total time is 14362749
total time is 17175068
 */
    private static void notInCacheLine() {
        long[][] arr = new long[LINE_NUM][COLUMN_NUM];
        long start = System.nanoTime();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                arr[j][i] = i * 2 + j;
            }
        }
        System.out.println("total time is " + (System.nanoTime() - start));
    }

    /*
total time is 5656175
total time is 6258547
total time is 4530605
total time is 6547718
total time is 5060662
total time is 6382038
 */
    private static void inCacheLine() {
        long[][] arr = new long[LINE_NUM][COLUMN_NUM];
        long start = System.nanoTime();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                arr[i][j] = i * 2 + j;
            }
        }
        System.out.println("total time is " + (System.nanoTime() - start));
    }
}
