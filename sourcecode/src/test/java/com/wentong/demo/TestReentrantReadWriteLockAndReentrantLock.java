package com.wentong.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReentrantReadWriteLockAndReentrantLock {

    public static void main(String[] args) throws Exception{
        SynchronizedArrayList synchronizedArrayList = new SynchronizedArrayList();
        CountDownLatch allStart = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    allStart.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    synchronizedArrayList.add(Thread.currentThread().getName() + j);
                }
                for (int j = 0; j < 1000; j++) {
                    synchronizedArrayList.get(j);
                }
            });
            thread.setName("thread:" + i);
            thread.start();
        }
    }


    static class SynchronizedArrayList {
        private List<String> list = new ArrayList<>();
        private List<String> synchronizedArrayList = Collections.synchronizedList(list);

        public void add(String ele) {
            sleepOneMills();
            synchronizedArrayList.add(ele);
        }

        public String get(int index) {
            sleepOneMills();
            return synchronizedArrayList.get(index);
        }

        public void remove(int index) {
            synchronizedArrayList.remove(index);
        }

        public int size() {
            return list.size();
        }
    }

    static class ReentrantReadWriteLockArrayList {
        private List<String> list = new ArrayList<>();
        private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        private Lock readLock = reentrantReadWriteLock.readLock();
        private Lock writeLock = reentrantReadWriteLock.writeLock();

        public void add(String ele) {
            sleepOneMills();
            writeLock.lock();
            try {
                list.add(ele);
            } finally {
                writeLock.unlock();
            }
        }

        public String get(int index) {
            sleepOneMills();
            readLock.lock();
            try {
                return list.get(index);
            } finally {
                readLock.unlock();
            }
        }

        public void remove(int index) {
            writeLock.lock();
            try {
                list.remove(index);
            } finally {
                writeLock.unlock();
            }
        }

        public int size() {
            return list.size();
        }

    }

    static class ReentrantLockArrayList {
        private List<String> list = new ArrayList<>();
        private ReentrantLock lock = new ReentrantLock();

        public void add(String ele) {
            sleepOneMills();
            lock.lock();
            try {
                list.add(ele);
            } finally {
                lock.unlock();
            }
        }

        public String get(int index) {
            sleepOneMills();
            lock.lock();
            try {
                return list.get(index);
            } finally {
                lock.unlock();
            }
        }

        public void remove(int index) {
            list.remove(index);
        }

        public int size() {
            return list.size();
        }

    }

    public static void sleepOneMills() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
