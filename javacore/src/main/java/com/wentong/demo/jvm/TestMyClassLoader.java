package com.wentong.demo.jvm;

public class TestMyClassLoader {

    public static void main(String[] args) throws Exception {
        MyClassLoader mcl = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent());
        Class<?> c1 = Class.forName("com.wentong.demo.jvm.Person", true, mcl);
        Object obj = c1.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }

}
