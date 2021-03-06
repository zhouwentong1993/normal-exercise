package com.wentong.concurrent;

import java.util.concurrent.CountDownLatch;

// time: 247
//10000000

//time: 1276
//100000000
public class SynAndCas {

    private static int count = 0;
    private static void incr() {
        synchronized (SynAndCas.class) {
            count++;
//            try {
//                TimeUnit.MILLISECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (count == 10000 * 10000) {
//                System.out.println("time: " + (System.currentTimeMillis() - l));
//                System.out.println(count);
//            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        long l = System.nanoTime();

        int nThreads = 100;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();
                    for (int j = 0; j < 100000; j++) {
                        incr();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }
        startLatch.countDown();
        endLatch.await();
        System.out.println("count is: " + count);
        System.out.println("time is: " + (System.nanoTime() - l) );
    }
}
