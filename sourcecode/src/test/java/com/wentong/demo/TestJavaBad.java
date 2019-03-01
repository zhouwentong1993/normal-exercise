package com.wentong.demo;

import org.junit.Test;

public class TestJavaBad {

    @Test
    public void testClassCast() {
        long l = Long.MAX_VALUE / 2;
        System.out.println(l);
    }
}
