package com.finup.demo.jvm;

public class JMH01 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        foo();
        System.out.println("time is: " + (System.currentTimeMillis() - start) + "ms");
    }

    static int foo() {
        int i = 0;
        while (i < 1000000000) {
            i++;
        }
        return i;
    }
}
