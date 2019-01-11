package com.finup.demo.concurrent;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitNotifyTest {
    private static final LinkedList<String> list = new LinkedList<>();
    private static final int MAX_SIZE = 10;
    private static AtomicInteger counter = new AtomicInteger(0);

    private static int produceIndex = 0;
    private static int consumeIndex = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <= 1000; i++) {
            Thread thread = new Thread(new ProduceThread());
            thread.setName("Produce " + produceIndex++);
            thread.start();
        }

        for (int i = 0; i <= 1000; i++) {
            Thread thread = new Thread(new ConsumeThread());
            thread.setName("Consume " + consumeIndex++);
            thread.start();
        }

    }

    static class ProduceThread implements Runnable {
        @Override
        public void run() {
            synchronized (list) {
                while (list.size() == MAX_SIZE) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String name = Thread.currentThread().getName();
                String elem = String.valueOf(counter.incrementAndGet());
                System.out.println("Thread: " + name + "添加了：" + elem);
                list.addFirst(elem);
                list.notifyAll();
            }
        }
    }

    static class ConsumeThread implements Runnable {
        @Override
        public void run() {
            synchronized (list) {
                while (list.isEmpty()) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String s = list.removeLast();
                String name = Thread.currentThread().getName();
                System.out.println("Thread: " + name + " remove s: " + s);
                list.notifyAll();
            }
        }
    }
}
