package com.wentong.demo.core;

public class CPUCacheLine2 {
    private static final int RUNS = 10;
    private static final int COLUMN = 1024 * 1024;
    private static final int LINE = 62;

    private static long[][] longs;

    public static void main(String[] args) throws Exception {
        longs = new long[COLUMN][];
        for (int i = 0; i < COLUMN; i++) {
            longs[i] = new long[LINE];
        }
        System.out.println("starting....");

        final long start = System.nanoTime();
        long sum = 0L;
        for (int r = 0; r < RUNS; r++) {
            // 1. slow
            // starting....
            //duration = 11251367085
//            for (int i = 0; i < LINE; i++) {
//                for (int j = 0; j < COLUMN; j++) {
//                    sum += longs[j][i];
//                }
//            }

            // 2. fast
            // starting....
            //duration = 1236470149
            for (int i = 0; i < COLUMN; i++) {
                for (int j = 0; j < LINE; j++) {
                    sum += longs[i][j];
                }
            }
        }
        System.out.println("duration = " + (System.nanoTime() - start));
    }
}
