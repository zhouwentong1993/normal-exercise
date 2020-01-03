package com.wentong.core;

import java.util.concurrent.TimeUnit;

/**
 * 测试 ThreadLocal 内存泄露
 */
public class ThreadLocalMemoryLeakTest {


    public static void main(String[] args) throws Exception {
        TimeUnit.SECONDS.sleep(15);
        ThreadLocal<BigObject> threadLocal = new ThreadLocal<>();
        BigObject value = new BigObject();
        threadLocal.set(value);
        value = null;
        System.gc();
        System.out.println("ok");
        TimeUnit.SECONDS.sleep(100);
    }
}

class BigObject {
    static byte[] bytes = new byte[20480];
    public BigObject() {
    }
}
