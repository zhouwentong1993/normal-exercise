package com.wentong.demo.dsaj;

public class TreeLinkedList implements Tree {

    // 根节点
    private Object elem;
    // 父节点
    private TreeLinkedList parent;
    // 自左向右第一个子节点
    private TreeLinkedList firstChild;
    // 自左向右第一个兄弟节点
    private TreeLinkedList nextSibling;


    @Override
    public Object getElem() {
        return elem;
    }

    @Override
    public Object setElem(Object obj) {
        Object temp = elem;
        elem = obj;
        return temp;
    }

    @Override
    public TreeLinkedList getParent() {
        return parent;
    }

    @Override
    public TreeLinkedList getFirstChild() {
        return firstChild;
    }

    @Override
    public TreeLinkedList getNextSibling() {
        return nextSibling;
    }

    @Override
    public int getHeight() {
        int height = 0;
        TreeLinkedList subTree = firstChild;

        while (subTree != null) {
            height = Math.max(subTree.getHeight(), height);
            subTree = subTree.nextSibling;
        }
        return height;
    }

    @Override
    public int getDepth() {
        int depth = 0;
        TreeLinkedList p = parent;
        while (p != null) {
            depth = depth + 1;
            p = p.getParent();
        }

        return depth;
    }

    public int getSize() {
        int size = 1;
        TreeLinkedList childTree = firstChild;

        while (childTree != null) {
            size += childTree.getSize();
            childTree = childTree.nextSibling;
        }
        return size;
    }
}
