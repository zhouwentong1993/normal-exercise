package com.wentong.demo;

public class TestCPU100Percent {

    public static void main(String[] args) {
        // 死循环会造成 100% CPU
        int i = 0;
        while (true) {
            i++;
        }


        // 在频繁分配大空间内存时，也会造成 CPU 100%
//        for (int i = 0; i < 100000; i++) {
//            String[] ss = new String[1024 * 10240];
//            ss[1000] = "aa";
//        }


        // 死锁不会导致 CPU 100%，线程数增多也不会。
//        Object o1 = new Object();
//        Object o2 = new Object();
//
//        new Thread(() -> {
//            synchronized (o1) {
//                SleepUtil.SleepOneSecond();
//                synchronized (o2) {
//
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            synchronized (o2) {
//                SleepUtil.SleepOneSecond();
//                synchronized (o1) {
//
//                }
//            }
//        }).start();

    }

}
