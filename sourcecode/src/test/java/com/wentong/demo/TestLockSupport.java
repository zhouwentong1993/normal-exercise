package com.wentong.demo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * test for lockSupport
 */
public class TestLockSupport {


    @Test
    public void testBaseUse1() {
        System.out.println("main start");
        LockSupport.park();
        System.out.println("main end");
    }


    /**
     * 为什么 testBaseUse3 的就可以先 park 后 unpark 呢？是线程不一致？不能吧，都是主线程啊。
     * 可能需要不同的线程去操作
     */
    @Test
    public void testBaseUse2() {
        System.out.println("main start");
        LockSupport.park();
        LockSupport.unpark(Thread.currentThread());
        System.out.println("main end");
    }

    @Test
    public void testBaseUse2_1() {
        Thread thread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("childThread unpark");
                LockSupport.unpark(thread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("main park");
        LockSupport.park();
    }

    @Test
    public void testBaseUse3() throws Exception {
        Thread thread = new Thread(() -> {
            System.out.println("childThread park");
            LockSupport.park();
            System.out.println("childThread unpark");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        LockSupport.unpark(thread);
        System.out.println("main end");
    }

    /**
     * 对线程 interrupt 也会导致 unpark
     */
    @Test
    public void testUnParkByInterrupt() throws Exception{
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("childThread park");
                LockSupport.park();
                System.out.println("childThread unpark");
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.interrupt();
    }

    @Test
    public void testParkWithThis() {
        LockSupport.park(this);
    }

    @Test(expected = IllegalMonitorStateException.class)
    public void testUnLockWhenNoLock() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.unlock();
    }

}
