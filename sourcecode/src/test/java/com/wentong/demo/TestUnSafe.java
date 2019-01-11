package com.wentong.demo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe 只能通过反射的方式获取，通过工厂方法无法获取，在代码里面写死了，判断的是 Bootstrap 启动器。
 */
public class TestUnSafe {

    static Unsafe unsafe = null;
    static long stateOffset = 0;
    private volatile long state = 0;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            stateOffset = unsafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestUnSafe testUnSafe = new TestUnSafe();
        boolean success = unsafe.compareAndSwapLong(testUnSafe, stateOffset, 0, 1);
        System.out.println(success);
    }


}
