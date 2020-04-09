package com.wentong.demo.core;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * 获取对象大小
 */
public class ObjectSize {

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }


    public static class A {
        int a;
        long c;
    }

    public static class B extends A {
        int b;
    }

    public static class C extends B {
        int c;
    }

}
