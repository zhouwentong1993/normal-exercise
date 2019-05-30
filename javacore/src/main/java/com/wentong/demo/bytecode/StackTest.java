package com.wentong.demo.bytecode;

/**
 * JVM 是基于栈实现的虚拟机
 */
public class StackTest {


    /*

    javap -c StackTest 之后的结果：先加载一个，再放入一个，将栈顶的两个弹出，相加之后然后放入的栈顶。

    Compiled from "StackTest.java"
public class com.wentong.demo.bytecode.StackTest {
  public com.wentong.demo.bytecode.StackTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void add(int, int);
    Code:
       0: iload_1
       1: iload_2
       2: iadd
       3: istore_3
       4: return

     */
    public void add(int a, int b) {
        int c = a + b;
    }

}
