package com.wentong.core;

/**
 * 测试 ThreadLocal 内存泄露
 */
public class ThreadLocalMemoryLeakTest {

    public static void main(String[] args) throws Exception {
        ThreadLocal<BigObject> threadLocal = new ThreadLocal<>();
        BigObject value = new BigObject();
        threadLocal.set(value);
        threadLocal = null;
        System.gc();
        System.out.println("ok");
    }
}

class BigObject {
    static byte[] bytes = new byte[20480];
}
