package com.wentong.concurrent;

public class JoinDemo {
    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            JoinThread joinThread = new JoinThread(currentThread);
            joinThread.setName("thread:" + i);
            joinThread.start();
            currentThread = joinThread;
        }
    }

    static class JoinThread extends Thread{
        private Thread previousThread;

        JoinThread(Thread previousThread) {
            this.previousThread = previousThread;
        }

        @Override
        public void run() {
            try {
                previousThread.join();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
