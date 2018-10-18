package com.wentong.demo;

import org.junit.Test;

public class ThreadPoolExecutorTest {

    @Test
    public void testIntegerSize() {
        int COUNT_BITS = Integer.SIZE - 3;
        System.out.println(COUNT_BITS);
         final int RUNNING    = -1 << COUNT_BITS;  // 29
        final int SHUTDOWN   =  0 << COUNT_BITS;   // -536870912
        final int STOP       =  1 << COUNT_BITS;   // 536870912
        final int TIDYING    =  2 << COUNT_BITS;   // 1073741824
        final int TERMINATED =  3 << COUNT_BITS;   // 1610612736
        final int CAPACITY = (1 << COUNT_BITS) - 1; // 536870911
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
        System.out.println(CAPACITY);
        System.out.println(29 & 536870911);
    }
}
