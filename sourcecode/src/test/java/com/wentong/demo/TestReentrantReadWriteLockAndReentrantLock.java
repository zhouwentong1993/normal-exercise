package com.wentong.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReentrantReadWriteLockAndReentrantLock {



    static class SynchronizedArrayList {
        private List<String> list = new ArrayList<>();
        private List<String> synchronizedArrayList = Collections.synchronizedList(list);

        public void add(String ele) {
            synchronizedArrayList.add(ele);
        }

        public String get(int index) {
            return synchronizedArrayList.get(index);
        }

        public void remove(int index) {
            synchronizedArrayList.remove(index);
        }
    }

    static class ReentrantReadWriteLockArrayList {
        private List<String> list = new ArrayList<>();
        private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        private Lock readLock = reentrantReadWriteLock.readLock();
        private Lock writeLock = reentrantReadWriteLock.writeLock();

        public void add(String ele) {
            writeLock.lock();
            try {
                list.add(ele);
            } finally {
                writeLock.unlock();
            }
        }

        public String get(int index) {
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

    }

    static class ReentrantLockArrayList {
        private List<String> list = new ArrayList<>();
        private ReentrantLock lock = new ReentrantLock();

        public void add(String ele) {
            lock.lock();
            try {
                list.add(ele);
            } finally {
                lock.unlock();
            }
        }

        public String get(int index) {
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


}
