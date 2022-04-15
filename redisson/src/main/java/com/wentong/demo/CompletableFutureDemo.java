package com.wentong.demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

    private static final String VALUE = "value";

    private static final Executor executor = Executors.newFixedThreadPool(4, r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public static void main(String[] args) {
        List<String> list = Arrays.asList("123", "456", "789");
        List<String> pricesAsync = findPricesAsync(list);
        System.out.println(pricesAsync);
    }

    private static List<String> findPricesAsync(List<String> shopList) {
        List<CompletableFuture<String>> list = shopList.stream().map(shop -> CompletableFuture.supplyAsync(() -> Thread.currentThread().getName(), executor)).collect(Collectors.toList());
        return list.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

}
