package com.wentong.demo.algorithm;

import java.util.Stack;

public class SumOfList1 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        listNode.next = new ListNode(9);
        listNode.next.next = new ListNode(9);
        listNode.next.next.next = new ListNode(9);
        listNode.next.next.next.next = new ListNode(9);
        listNode.next.next.next.next.next = new ListNode(9);
        listNode.next.next.next.next.next.next = new ListNode(9);
        listNode.next.next.next.next.next.next.next = new ListNode(9);
        listNode.next.next.next.next.next.next.next.next = new ListNode(9);
        listNode.next.next.next.next.next.next.next.next.next =  new ListNode(9);
        ListNode listNode1 = new ListNode(7);
        new SumOfList1().addTwoNumbers(listNode, listNode1);
    }

    // 这个方法不适用于数量太多的情况。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long c1 = 0;
        long c2 = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                c1 = 10 * c1 + l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                c2 = 10 * c2 + l2.val;
                l2 = l2.next;
            }
        }
        long c3 = c1 + c2;
        Stack<Long> stack = new Stack<>();
        while (c3 / 10 >= 0) {
            stack.push(c3 % 10);
            c3 = c3 / 10;
            if (c3 == 0) {
                break;
            }
        }
        ListNode listNode = new ListNode(0);
        ListNode head = listNode;
        while (!stack.isEmpty()) {
            listNode.next = new ListNode(Math.toIntExact(stack.pop()));
            listNode = listNode.next;
        }
        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
