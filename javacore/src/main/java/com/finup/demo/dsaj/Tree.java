package com.finup.demo.dsaj;

/**
 * 数据结构课本中的树 ADT
 */
public interface Tree {

    //返回当前节点中存放的对象
    Object getElem(); //将对象obj存入当前节点，并返回此前的内容

    Object setElem(Object obj);

    //返回当前节点的父节点
    TreeLinkedList getParent();

    //返回当前节点的长子
    TreeLinkedList getFirstChild();

    //返回当前节点的最大弟弟
    TreeLinkedList getNextSibling();

    // 返回当前节点后代元素的数目，即以当前节点为根的子树的规模 public int getSize()
    // 返回当前节点的高度
    int getHeight();

    //返回当前节点的深度
    int getDepth();
}
