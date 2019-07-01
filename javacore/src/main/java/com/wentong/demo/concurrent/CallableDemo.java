package com.wentong.demo.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100, new ThreadFactoryBuilder().setNameFormat("thread-%d").build());
        List<Future> futures = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Future submit = executorService.submit(new Task());
            futures.add(submit);
        }
        for (Future future : futures) {
            System.out.println(future.get());
        }
    }

    static class Task implements Callable {

        @Override
        public Integer call() throws InterruptedException {
            Random random = new Random();
            int timeout = random.nextInt(4);
            TimeUnit.SECONDS.sleep(timeout);
            System.out.println(Thread.currentThread().getName());
            return timeout;
        }
    }

}
