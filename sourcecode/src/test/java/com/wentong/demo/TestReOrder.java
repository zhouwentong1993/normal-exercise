package com.wentong.demo;

import java.util.concurrent.TimeUnit;

public class TestReOrder {

    static boolean ready = false;
    static int num = 0;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100000; i++) {
            Thread readThread = new Thread(new ReadThread());
            readThread.start();

            Thread writeThread = new Thread(new WriteThread());
            writeThread.start();

            TimeUnit.MICROSECONDS.sleep(1);
            readThread.interrupt();
            System.out.println("main end");

        }
    }

    static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {
                    if (num == 0) {
                        System.err.println("!!!!!!!!!!!!!!!!!!!!!num == 0!!!!!!!");

                    }
                    System.out.println(num + num);
                    System.out.println("read thread end");
                }
            }
        }
    }

    static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write thread end");
        }
    }

}
