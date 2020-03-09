package com.wentong.guava;

import org.jetbrains.annotations.NotNull;

public class TestNullable {

    public static void main(String[] args) {
        testNull(null);
    }

    public static void testNull(@NotNull(exception = NullPointerException.class) String mobile) {
        System.out.println(mobile);
    }

    public static void testNull1() {

    }

}
