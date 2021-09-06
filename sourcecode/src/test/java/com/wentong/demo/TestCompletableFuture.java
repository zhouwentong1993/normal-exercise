package com.wentong.demo;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestCompletableFuture {

    @Test(expected = ExecutionException.class)
    public void testJoin() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });
//        future.join();
        future.get();
    }

    @Test
    public void testBasicUse() throws Exception {

    }

}
