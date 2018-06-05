package com.finup.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhouwentong on 2018/6/5.
 */
public class TwinsLock implements Lock{

    Sync sync = new Sync(2);

    static class Sync extends AbstractQueuedLongSynchronizer {


        public Sync(int count) {
            if (count < 0) {
                throw new IllegalArgumentException();
            }
            setState(count);
        }

        @Override
        protected long tryAcquireShared(long reduceCount) {
            for (;;) {
                long state = getState();
                System.out.println("acquireShared invoked,state is :" + state);
                long count = state - reduceCount;
                if (count < 0 || compareAndSetState(state, count)) {
                    return count;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(long incrementCount) {
            for (;;) {
                long state = getState();
                System.out.println("releaseShared invoke,state is :" + state);
                long count = state + incrementCount;
                if (compareAndSetState(state, count)) {
                    return true;
                }
            }
        }
    }


    @Override
    public void lock() {
        // 为什么是 1
        sync.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // 为什么是 1
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
