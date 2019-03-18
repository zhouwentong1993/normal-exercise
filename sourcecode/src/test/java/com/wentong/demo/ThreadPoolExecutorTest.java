package com.wentong.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import util.SleepUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    @Test
    public void testIntegerSize() {
        int COUNT_BITS = Integer.SIZE - 3;
        final int RUNNING = -1 << COUNT_BITS;  // 29
        final int SHUTDOWN = 0 << COUNT_BITS;   // -536870912
        final int STOP = 1 << COUNT_BITS;   // 536870912
        final int TIDYING = 2 << COUNT_BITS;   // 1073741824
        final int TERMINATED = 3 << COUNT_BITS;   // 1610612736
        final int CAPACITY = (1 << COUNT_BITS) - 1; // 536870911
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
        System.out.println(CAPACITY);
        System.out.println(29 & 536870911);
    }

    @Test
    public void testTryTerminate() throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 200, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new Worker());

        }
        monitor(threadPoolExecutor);

        // shutdown 之后不允许再提交了，抛异常
        threadPoolExecutor.shutdown();
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new Worker());

        }

        TimeUnit.SECONDS.sleep(1000);
    }

    @Test
    public void testDeadLoopBreak() {
        label:
        for (; ; ) {
            System.out.println("outer loop");
            for (; ; ) {
                break label;
            }
        }
    }

    @Test
    public void testWorkerThreadDie() throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 200, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), new ThreadFactoryBuilder().setNameFormat("thread:%s").build());
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new Worker());
        }

        threadPoolExecutor.execute(new Worker());
        TimeUnit.SECONDS.sleep(13);
    }

    @Test
    public void testWhenThreadDie() {
        Thread thread = new Thread(() -> {
            SleepUtil.sleepOneSecond();
            System.out.println("ok,I am over!");
        });
        thread.run();
        SleepUtil.sleepSeconds(2);
        thread.start();
    }


    private void monitor(ThreadPoolExecutor threadPoolExecutor) {
        new Thread(() -> {
            int time = 0;
            while (true) {
                time = time + 1;
                System.out.println("activeCount:" + threadPoolExecutor.getActiveCount());
                System.out.println("corePoolSize:" + threadPoolExecutor.getCorePoolSize());
                System.out.println("completedTaskCount:" + threadPoolExecutor.getCompletedTaskCount());
                System.out.println("largestPoolSize:" + threadPoolExecutor.getLargestPoolSize());
                System.out.println("maximumPoolSize:" + threadPoolExecutor.getMaximumPoolSize());
                System.out.println();
                System.out.println();
                SleepUtil.sleepOneSecond();
            }
        }).start();
    }

    static class WorkerThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Start. Command = ");
            System.out.println(Thread.currentThread().getName() + " End.");
        }

        private void processCommand() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Worker implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

}
