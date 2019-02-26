package com.wentong.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestJPS {


    @Test
    public void testJps() throws Exception{
        TimeUnit.SECONDS.sleep(30);
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            list.add(new byte[1024 * 20]);
        }
        TimeUnit.SECONDS.sleep(100);
    }
}
