package com.wentong.demo.core;

/*
-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 */
public class JHSDBTest {

    static class Test {
        static ObjectHolder objectHolder = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder objectHolder = new ObjectHolder();
            System.out.println("done");
            // 调用一次 hashCode 方法
            int i = objectHolder.hashCode();
            System.out.println(i);

        }

    }

    private static class ObjectHolder {
        int a;
        long b;
        Object c;
        Integer d;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.foo();
        System.out.println(test.hashCode());
    }
}
