package com.wentong.concurrent;

public class InterruptDemo {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread busyThread = new Thread(() -> {
            while (true) {

            }
        });

        sleepThread.start();
        busyThread.start();

        sleepThread.interrupt();
        busyThread.interrupt();

        while (sleepThread.isInterrupted()){};

        System.out.println("sleepThread:" + sleepThread.isInterrupted());
        System.out.println("busyThread:" + busyThread.isInterrupted());
    }
}
