package com.wentong.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class TestFuture {

    ExecutorService executor = Executors.newFixedThreadPool(10);
    ArchiveSearcher searcher = new Searcher();

    @Test
    public void showSearch() throws Exception {
        Future<String> future
                = executor.submit(() -> searcher.search("target"));
        FutureTask<String> futureTask = (FutureTask<String>) future;
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(10);
        Assert.assertEquals("real search target", future.get()); // use future
    }
}

interface ArchiveSearcher {
    String search(String target) throws Exception;
}

class Searcher implements ArchiveSearcher {

    @Override
    public String search(String target) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return "real search " + target;
    }
}
