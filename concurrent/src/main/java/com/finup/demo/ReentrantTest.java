package com.finup.demo;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhouwentong on 2018/5/4.
 * 重入锁 test
 */
public class ReentrantTest {
    public static void main(String[] args) throws Exception {
        method1();
    }

    public static synchronized void method1() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        method2();
    }

    public static synchronized void method2() throws Exception{
        System.out.println("now method2");
        TimeUnit.SECONDS.sleep(1);

    }
}
