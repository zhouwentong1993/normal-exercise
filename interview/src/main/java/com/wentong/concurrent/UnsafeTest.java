package com.wentong.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
    public static void main(String[] args)  throws Exception{
        // 不可用
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println(unsafe);
        // 需要通过反射获取
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        // 线程相关
        Thread thread = new Thread(() -> {
            unsafe.park(true, System.currentTimeMillis() + 3000);
            System.out.println("I am thread");
        });
        thread.start();
        unsafe.unpark(thread);

        int i = unsafe.addressSize();
        int pageSize = unsafe.pageSize();
        System.out.println(i);
        System.out.println(pageSize);

        // CAS 操作
        Data data = new Data();
        data.setId(1);
        Field idField = data.getClass().getDeclaredField("id");
        unsafe.compareAndSwapInt(data, unsafe.objectFieldOffset(idField), 1, 100);
        System.out.println(data.getId());
        // 获取 data 实例的偏移量为 xx 的 int 值
        int anInt = unsafe.getInt(data, unsafe.objectFieldOffset(idField));
        System.out.println(anInt);

        // 堆外内存

        // 在 Java 中不支持数组大小超过 int 最大值
//        int[] arr = new int[Integer.MAX_VALUE + 10];
//        System.out.println(arr.length);
        // 这样相当于生成了一个更大的数组
        long l = unsafe.allocateMemory(2L * Integer.MAX_VALUE);
        unsafe.putByte(l + 2, (byte) 1);
        System.out.println(unsafe.getByte(l + 2));

    }

    static class Data {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
