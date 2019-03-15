package com.wentong.demo.concurrent;

public class Class01 {

    private static volatile int count = 0;

    private static void add10k() {
        int idx = 1 ;
        while (idx <= 10000) {
            add();
            idx++;
        }
    }
    private static synchronized void add() {
        count++;
    }

    public static void main(String[] args) throws Exception{
        Thread thread1 = new Thread(Class01::add10k);
        Thread thread2 = new Thread(Class01::add10k);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);

    }
}
