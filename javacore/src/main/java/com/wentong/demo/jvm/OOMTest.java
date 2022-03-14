package com.wentong.demo.jvm;

import java.util.concurrent.TimeUnit;

public class OOMTest {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100000; i++) {
            Object o = new Object();

        }
        TimeUnit.SECONDS.sleep(100);
    }

}
