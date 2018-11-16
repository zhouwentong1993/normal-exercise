package com.finup.demo.wheel;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E> {

    static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;

    // 当前 size
    private AtomicInteger size = new AtomicInteger(0);
    // 最大容量
    private int capacity;


    private final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition();

    private final ReentrantLock putLock = new ReentrantLock();
    private final Condition notFull = putLock.newCondition();


    public LinkedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public LinkedBlockingQueue() {
        this.capacity = Integer.MAX_VALUE;
    }

    private void lockAll() {
        takeLock.lock();
        putLock.lock();
    }

    private void unLockAll() {
        takeLock.unlock();
        putLock.unlock();
    }

    private void enqueue(Node<E> node) {
        tail.next = node;
        tail = node;
    }

    private Node<E> dequeue() {
        Node<E> result = head;
        head = head.next;
        return result;
    }

    private boolean isFull() {
        return size.intValue() >= capacity;
    }


    private void signalNotFull() {
        ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    private void signalNotEmpty() {
        ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(E e) throws InterruptedException {
        int c = -1;
        ReentrantLock putLock = this.putLock;
        AtomicInteger size = this.size;
        putLock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }
            enqueue(new Node<>(e));
            c = size.getAndIncrement();
            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            putLock.unlock();
        }
        if (c >= 0) {
            signalNotEmpty();
        }
    }


    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        int c = -1;
        ReentrantLock putLock = this.putLock;
        AtomicInteger size = this.size;
        putLock.lock();
        long nanos = unit.toNanos(timeout);
        try {
            while (isFull()) {
                if (nanos < 0) {
                    return false;
                }
                nanos = notFull.awaitNanos(nanos);
            }

            enqueue(new Node<>(e));
            c = size.getAndIncrement();
            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            putLock.unlock();
        }

        if (c >= 0) {
            signalNotEmpty();
        }
        return true;
    }

    @Override
    public boolean offer(E e) {
        int c = -1;
        ReentrantLock putLock = this.putLock;
        AtomicInteger size = this.size;

        putLock.lock();
        try {
            if (isFull()) {
                return false;
            } else {
                enqueue(new Node<>(e));
                c = size.getAndIncrement();
                if (c + 1 < capacity) {
                    signalNotFull();
                }
            }
        } finally {
            putLock.unlock();
        }
        if (c >= 0) {
            signalNotEmpty();
        }
        return false;
    }

    @Override
    public E take() throws InterruptedException {
        int c = -1;
        ReentrantLock takeLock = this.takeLock;
        AtomicInteger size = this.size;
        E result;
        takeLock.lock();
        try {
            while (size.get() == 0) {
                notEmpty.await();
            }
            Node<E> node = dequeue();
            result = node.item;
            c = size.getAndDecrement();
            if (c - 1 > 0) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c <= capacity) {
            signalNotFull();
        }

        return result;
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        int c = -1;
        ReentrantLock takeLock = this.takeLock;
        AtomicInteger size = this.size;
        takeLock.lock();
        E result;
        try {
            long nanos = unit.toNanos(timeout);
            while (size.get() == 0) {
                if (nanos == 0) {
                    return null;
                }
                nanos = notFull.awaitNanos(nanos);
            }
            result = dequeue().item;
            c = size.getAndDecrement();
            if (c - 1 > 0) {
                notFull.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c < capacity) {
            signalNotEmpty();
        }
        return result;
    }

    @Override
    public int remainingCapacity() {
        return capacity - size.intValue();
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
