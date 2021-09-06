package com.wentong.demo;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 异步处理的方式，如果是同步等待结果执行结束时，那么后续的代码就会处于阻塞状态。
public class TestListenableFuture {

    @Test
    public void testBasicUse() throws Exception {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> explosion = service.submit((Callable) () -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        });
        Futures.addCallback(explosion, new FutureCallback<String>() {
            // we want this handler to run immediately after we push the big red button!
            public void onSuccess(String explosion) {
                System.out.println(Thread.currentThread().getName());
                System.out.println("TestListenableFuture.onSuccess");
            }

            public void onFailure(Throwable thrown) {
                System.out.println("TestListenableFuture.onFailure");
            }
        });
        System.out.println("block?");
        TimeUnit.SECONDS.sleep(10);
    }

}
