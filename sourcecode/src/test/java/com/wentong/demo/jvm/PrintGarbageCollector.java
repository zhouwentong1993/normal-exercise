package com.wentong.demo.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class PrintGarbageCollector {
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }
    }

}
