package com.wentong.demo.core;

import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(() -> {
            try {
                threadDemo.m1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(threadDemo::m2).start();

    }

    public synchronized void m1() throws Exception {
        System.out.println("ThreadDemo.m1");
        TimeUnit.SECONDS.sleep(1000);
    }

    public synchronized void m2() {
        System.out.println("ThreadDemo.m2");
    }

}
