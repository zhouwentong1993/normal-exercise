package com.wentong.demo.concurrent;

public class ThreadSafeDemo {

    private int count;

    private void nonSafeAction() {
        while (count <= 100000) {
            synchronized (this) {
                int first = count++;
                int second = count;
                if (first != second - 1) {
                    System.out.println("出现竞争：first = " + first + " second = " + second);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadSafeDemo threadSafeDemo = new ThreadSafeDemo();
        Thread thread1 = new Thread(() -> {
            threadSafeDemo.nonSafeAction();
        });
        thread1.setName("first");
        Thread thread2 = new Thread(() -> {
            threadSafeDemo.nonSafeAction();
        });
        thread2.setName("second");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
