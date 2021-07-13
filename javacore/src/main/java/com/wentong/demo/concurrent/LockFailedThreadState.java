package com.wentong.demo.concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockFailedThreadState {

    public static void main(String[] args) {
        Object object = new Object();
        synchronized (object) {
            for (int i = 0; i < 100; i++) {
                new Thread(LockSupport::park, "").start();
            }

        }
    }

}
