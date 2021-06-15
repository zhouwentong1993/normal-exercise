package com.wentong.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import util.SleepUtil;

import java.util.concurrent.*;

public class TestThreadPoolExecutor {

    // 当添加到 27 个元素时，报错，拒绝。
    @Test
    public void testBasicUsage() throws Exception {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build(), new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread name: " + Thread.currentThread().getName());
            });
//            System.out.println(i);
            threadPool.execute(thread);
            monitor(threadPool);
        }
        Thread.currentThread().join();
    }

    @Test
    public void testSubmit() throws Exception {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build(), new ThreadPoolExecutor.DiscardPolicy());
        Call task = new Call(100);
        Future<Integer> submit = threadPool.submit(task);
        System.out.println(submit.get());
    }


    class Call implements Callable<Integer> {

        private int num;

        public Call(int num) {
            this.num = num;
        }

        @Override
        public Integer call() throws Exception {
            int result = 1;

            for (int i = 2; i <= num; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }

            System.out.printf("Factorial of %d is :: %d\n", num, result);
            return result;
        }
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
                System.out.println("threadPoolSize:" + threadPoolExecutor.getQueue().size());
                System.out.println();
                SleepUtil.sleepOneSecond();
            }
        }).start();
    }
}
