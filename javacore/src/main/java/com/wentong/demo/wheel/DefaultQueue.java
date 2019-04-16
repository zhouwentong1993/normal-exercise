package com.wentong.demo.wheel;

/**
 * 队列基本结构
 */
public class DefaultQueue<T> implements Queue<T> {

    private int capacity;

    private int size;

    private Node<T> head;

    private Node<T> tail;

    // 无界队列，默认为无限。
    private static final int DEFAULT_CAPACITY = Integer.MAX_VALUE;

    private static class Node<T> {
        Node<T> prev;
        Node<T> next;
        T data;

        Node(Node<T> prev, Node<T> next, T data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    public DefaultQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    public DefaultQueue() {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    public DefaultQueue(Node<T> root) {
        this.capacity = DEFAULT_CAPACITY;
        this.head = root;
        this.tail = root;
        this.size = 1;
    }


    @Override
    public boolean put(T t) {
        if (checkForAdd()) {
            Node<T> newHead = new Node<>(null, head, t);
            head.prev = newHead;
            head = newHead;
            size = size + 1;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T pop() {
        if (size > 0) {
            Node<T> oldTail = tail;
            T data = oldTail.data;
            Node<T> newTail = oldTail.prev;
            newTail.next = null;
            tail = newTail;
            size = size - 1;
            return data;
        } else {
            return null;
        }
    }

    @Override
    public T peek() {
        if (size > 0) {
            return tail.data;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    private boolean checkForAdd() {
        return size <= capacity - 1;
    }


}
