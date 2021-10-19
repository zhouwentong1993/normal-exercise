package com.wentong.demo;

import com.google.common.base.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestFuture {

    ExecutorService executor = Executors.newFixedThreadPool(10);
    ArchiveSearcher searcher = new Searcher();

    // Future 提供了返回任务状态的方法，这样就能在多个任务执行时，执行时间取任务中最小的那个。
    @Test
    public void showSearch() throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> target = executor.submit(() -> searcher.search("target"));
            futures.add(target);
        }
        for (int i = 0; i < 10; i++) {
            futures.get(i).get();
        }
        stopwatch.stop();
        System.out.println(stopwatch.toString());
//        FutureTask<String> futureTask = (FutureTask<String>) future;
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                try {
//                    future.get();
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//        TimeUnit.SECONDS.sleep(10);
//        Assert.assertEquals("real search target", future.get()); // use future
    }
}

interface ArchiveSearcher {
    String search(String target) throws Exception;
}

class Searcher implements ArchiveSearcher {

    @Override
    public String search(String target) throws Exception {
        TimeUnit.SECONDS.sleep(4);
        return "real search " + target;
    }
}
