package com.wentong.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestArrayListAndLinkedList {

    @Test
    public void testArrayList() {
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>(100000);
        int cap = 100000;
        for (int i = 0; i < cap; i++) {
            list.add(i);
        }
        System.out.println("time :" + (System.currentTimeMillis() - start));

        for (int i = 0; i < cap; i++) {
            list.get(i);
        }
    }

    @Test
    public void testLinkedList() {
        long start = System.currentTimeMillis();
        List<Integer> list = new LinkedList<>();
        int cap = 100000;
        for (int i = 0; i < cap; i++) {
            list.add(i);
        }
        System.out.println("time :" + (System.currentTimeMillis() - start));

//        for (int i = 0; i < cap; i++) {
//            list.get(i);
//        }
    }

}
