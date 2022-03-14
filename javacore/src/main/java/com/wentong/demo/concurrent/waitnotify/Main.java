package com.wentong.demo.concurrent.waitnotify;

import org.junit.Assert;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    // 50 线程读，50 线程写。超时 10 线程。
    public static void main(String[] args) throws Exception {
//        normalTest();
        performanceTest();
    }

    private static void performanceTest() throws Exception {
        SyncResponse syncResponse = new SyncResponse();
        final int WRITE_THREAD_COUNT = 50;
        final int LOOP_PER_THREAD = 50;
        final int READ_THREAD_COUNT = 50;
        CountDownLatch writeLatch = new CountDownLatch(WRITE_THREAD_COUNT);
        CountDownLatch readLatch = new CountDownLatch(READ_THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(1);

        Response[] responses = new Response[WRITE_THREAD_COUNT];
        AtomicInteger count = new AtomicInteger();

        for (int i = 0; i < WRITE_THREAD_COUNT; i++) {
            int finalI = i;
            new Thread(() -> {
                Response response = syncResponse.createResponse(UUID.randomUUID().toString());
                responses[finalI] = response;
                readLatch.countDown();
                Result result = response.get(10, TimeUnit.SECONDS);
                if (result != null) {
                    count.getAndIncrement();
                }
            }).start();
        }
        readLatch.await();
        for (int i = 0; i < READ_THREAD_COUNT; i++) {
            Response response = responses[i];
            response.putResult(new Result());
        }
        TimeUnit.SECONDS.sleep(10);
        Assert.assertEquals(50, count.intValue());
    }

    private static void normalTest() {
        SyncResponse syncResponse = new SyncResponse();
        Response response = syncResponse.createResponse(UUID.randomUUID().toString());
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                response.putResult(new Result());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Result result = response.get(10, TimeUnit.SECONDS);
        Assert.assertNotNull(result);

    }

}
