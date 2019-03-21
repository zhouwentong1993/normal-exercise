package com.wentong.demo.oom;


/**
 * 构造栈溢出异常，貌似无法构造出来。周志明的书里
 * -Xss160k
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable throwable) {
            System.out.println("stack length:" + javaVMStackSOF.stackLength);
            throw throwable;
        }
    }
}
