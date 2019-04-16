package com.wentong.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhouwentong on 2018/6/5.
 */
public class TwinsLock implements Lock{

//    final Sync sync = new Sync(2);
//
//    final static class Sync extends AbstractQueuedLongSynchronizer {
//
//        public Sync(int count) {
//            if (count < 0) {
//                throw new IllegalArgumentException();
//            }
//            setState(count);
//        }
//
//        @Override
//        protected long tryAcquireShared(long reduceCount) {
//            for (;;) {
//                long state = getState();
//                System.out.println("acquireShared invoked,state is :" + state);
//                long count = state - reduceCount;
//                System.out.println("acquireShared invoked,count is :" + count);
//                if (count < 0 || compareAndSetState(state, count)) {
//                    System.out.println("acquireShared after,state is :" + getState());
//                    return count;
//                }
//            }
//        }
//
//        @Override
//        protected boolean tryReleaseShared(long incrementCount) {
//            for (;;) {
//                long state = getState();
//                System.out.println("releaseShared invoke,state is :" + state);
//                long count = state + incrementCount;
//                System.out.println("releaseShared invoke,count is :" + count);
//                if (compareAndSetState(state, count)) {
//                    return true;
//                }
//            }
//        }
//        final ConditionObject newCondition() {
//            return new ConditionObject();
//        }
//    }
//
//
////    @Override
////    public void lock() {
////        // 为什么是 1
////        sync.acquireShared(1);
////    }
////
////    @Override
////    public void lockInterruptibly() throws InterruptedException {
////
////    }
////
////    @Override
////    public boolean tryLock() {
////        return false;
////    }
////
////    @Override
////    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
////        return false;
////    }
////
////    @Override
////    public void unlock() {
////        // 为什么是 1
////        sync.releaseShared(1);
////    }
////
////    @Override
////    public Condition newCondition() {
////        return null;
////    }
//
//    @Override
//    public void lock() {
//        sync.acquireShared(1);
//    }
//
//    @Override
//    public void unlock() {
//        sync.releaseShared(1);
//    }
//
//    @Override
//    public void lockInterruptibly() throws InterruptedException {
//        sync.acquireSharedInterruptibly(1);
//    }
//
//    @Override
//    public boolean tryLock() {
//        return sync.tryAcquireShared(1) >= 0;
//    }
//
//    @Override
//    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
//        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
//    }
//
//    @Override
//    public Condition newCondition() {
//        return newCondition();
//    }
private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -7889272986162341211L;

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }

        public int tryAcquireShared(int reduceCount) {
            for (;;) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        public boolean tryReleaseShared(int returnCount) {
            for (;;) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

    public void lock() {
        sync.acquireShared(1);
    }

    public void unlock() {
        sync.releaseShared(1);
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
