package com.wentong.demo.concurrent.waitnotify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Response {

    private boolean done = false;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Result result = null;

    private final String id;

    public Response(String id) {
        this.id = id;
    }

    public void putResult(Result result) {
        lock.lock();
        try {
            this.result = result;
            this.done = true;
            this.condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Result get(long timeout, TimeUnit timeUnit) {
        long deadline = System.currentTimeMillis() + timeUnit.toMillis(timeout);
        lock.lock();
        try {
            while (!done) {
                long remain = deadline - System.currentTimeMillis();
                if (remain <= 0) {
                    return result;
                } else {
                    condition.await(remain, TimeUnit.MILLISECONDS);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }

}
