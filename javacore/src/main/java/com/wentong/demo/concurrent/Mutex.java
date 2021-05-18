package com.wentong.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// 通过 AQS 定义了一个独占的，非重入锁。
public class Mutex implements Lock {

    public static void main(String[] args) throws Exception {
        Mutex mutex = new Mutex();
        new Thread(() -> {
            mutex.lock();
            System.out.println(Thread.currentThread().getName() + " get lock");
        }, "Thread1").start();
        new Thread(() -> {
            mutex.lock();
            System.out.println(Thread.currentThread().getName() + " get lock");
        }, "Thread2").start();

        TimeUnit.SECONDS.sleep(12);
    }

    public static class sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            checkArg(arg);
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else {
                return false;
            }
        }

        private void checkArg(int arg) {
            if (arg != 1) {
                throw new IllegalArgumentException();
            }
        }

        @Override
        protected boolean tryRelease(int arg) {
            checkArg(arg);
            // 当前线程获取到锁，如果当前状态是 0，代表锁被改动过，抛出异常，提前结束
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 判断该锁是否被占有
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }

    }

    public static final sync sync = new sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

}
