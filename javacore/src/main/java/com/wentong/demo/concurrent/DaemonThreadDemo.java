package com.wentong.demo.concurrent;

/**
 * 守护线程会随着主线程的结束而结束
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {
            }
        });
        thread.setDaemon(true);
        thread.start();

        System.out.println("thread end");
    }
}
