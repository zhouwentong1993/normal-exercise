package com.wentong.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestFuture {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    ArchiveSearcher searcher = new Searcher();

    @Test
    public void showSearch()
            throws InterruptedException {
        Future<String> future
                = executor.submit(() -> searcher.search("target"));
        FutureTask<String> futureTask = (FutureTask<String>) future;
        printTime(futureTask);
        TimeUnit.SECONDS.sleep(6);
        try {
            Assert.assertEquals("real search target", future.get()); // use future
        } catch (ExecutionException ex) {
            throw new IllegalAccessError();
        }
    }

    private static void printTime(FutureTask<String> futureTask) {
        AtomicInteger i = new AtomicInteger(1);
        new Thread(() -> {
            for (; ; ) {
                System.out.println(i.getAndIncrement());
                System.out.println(futureTask.isDone());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}


interface ArchiveSearcher {
    String search(String target);
}

class Searcher implements ArchiveSearcher {

    @Override
    public String search(String target) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "real search " + target;
    }
}
