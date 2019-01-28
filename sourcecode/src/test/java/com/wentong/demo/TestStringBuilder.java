package com.wentong.demo;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class TestStringBuilder {

    @Test
    public void testBigString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(i);
        }
        LockSupport.park(this);
        System.out.println(stringBuilder);
    }
}
