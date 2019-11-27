package com.wentong.concurrent;

import java.util.concurrent.TimeUnit;

public class SynAndCas {

    private static int count = 0;
    static long l = System.currentTimeMillis();

    private static void incr() {
        synchronized (SynAndCas.class) {
            count++;
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 1000 * 10000) {
                System.out.println("time: " + (System.currentTimeMillis() - l));
                System.out.println(count);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    incr();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
