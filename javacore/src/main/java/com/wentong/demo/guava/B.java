package com.wentong.demo.guava;

class A {
    public A() {
        System.out.println("A1");
    }

    {
        System.out.println("A2");
    }

    static {
        System.out.println("A3");
    }
}

public class B extends A {
    public B() {
        System.out.println("B1");
    }

    {
        System.out.println("B2");
    }

    static {
        System.out.println("B3");
    }

    public static void main(String[] args) {
        A b = new B();
    }
}
