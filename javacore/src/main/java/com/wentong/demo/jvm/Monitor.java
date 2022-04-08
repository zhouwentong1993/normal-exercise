package com.wentong.demo.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class Monitor {

    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.count++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.exit(1);
                }
            }
        }).start();
        Unsafe unsafe = reflectGetUnsafe();
        long offset = unsafe.objectFieldOffset(counter.getClass().getField("count"));
        Object value = unsafe.getObject(counter, offset);
        System.out.println(value);
    }

    private static Unsafe reflectGetUnsafe() throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }

}
