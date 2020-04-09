package com.wentong.demo.core;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * 获取对象大小
 */
public class ObjectSize {
//    public static void main(String[] args) {
//        String s = RamUsageEstimator.humanSizeOf(new Object());
//        System.out.println(s);
//
//    }

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    public static class A {
        boolean f;
    }
}
