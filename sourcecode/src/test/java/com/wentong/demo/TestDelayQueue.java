package com.wentong.demo;

import com.google.common.primitives.Ints;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestDelayQueue {

    @Test
    public void basicUse() throws Exception {
        DelayQueue<MyDelayed> delayeds = new DelayQueue<>();
        for (int i = 0; i < 10; i++) {
            MyDelayed delayed = new MyDelayed("data", 1000);
            TimeUnit.SECONDS.sleep(1);
            delayeds.add(delayed);
        }

        printSeconds();
        for (; ; ) {
            MyDelayed poll = delayeds.poll();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(poll);
        }

    }

    private void printSeconds() {
        AtomicInteger start = new AtomicInteger(0);
        new Thread(() -> {
            for (; ; ) {
                System.out.println(start.getAndIncrement());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class MyDelayed implements Delayed {
        String data;
        long deadLine;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public long getDeadLine() {
            return deadLine;
        }

        public void setDeadLine(long deadLine) {
            this.deadLine = deadLine;
        }

        public MyDelayed(String data, long delayMill) {
            this.data = data;
            this.deadLine = delayMill + System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(deadLine - System.currentTimeMillis(), unit);
        }

        @Override
        public int compareTo(Delayed o) {
            return Ints.saturatedCast(this.deadLine - ((MyDelayed) o).getDeadLine());
        }
    }

    @Test
    public void testPriorityQueue() throws Exception {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 100; i++) {
            priorityQueue.add(String.valueOf(i));
        }

        System.out.println(priorityQueue);
    }
}
