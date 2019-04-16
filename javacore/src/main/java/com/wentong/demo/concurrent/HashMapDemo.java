package com.wentong.demo.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class HashMapDemo {
    @Test
    public void testDupKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        map.put("a", "b");
        Assert.assertEquals(map.get("a"), "b");
    }

    @Test
    public void testMapThread() throws Exception {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(String.valueOf(finalI), UUID.randomUUID().toString());
            }).start();
        }
        TimeUnit.SECONDS.sleep(100);
    }

}
