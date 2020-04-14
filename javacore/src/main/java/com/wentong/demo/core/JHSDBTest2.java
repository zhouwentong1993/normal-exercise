package com.wentong.demo.core;

/**
 * t1,t2,t3 这三个变量的位置在哪儿？
 * t1 是静态变量，在 JVM 的方法区里面
 * t2 是实例变量，在堆内存里面
 * t3 是在线程的调用栈里面
 */
public class JHSDBTest2 {
    static Test t1 = new Test();
    Test t2 = new Test();
    public void foo() {
        Test t3 = new Test();
    }

    public static void main(String[] args) {
        new JHSDBTest2().foo();
        System.out.println("ok");
    }
}

class Test{

}
