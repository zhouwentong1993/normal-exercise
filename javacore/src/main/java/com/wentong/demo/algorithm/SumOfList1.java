package com.wentong.demo.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
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
        listNode.next.next.next.next.next.next.next.next.next = new ListNode(9);
        ListNode listNode1 = new ListNode(7);
        new SumOfList1().addTwoNumbers(listNode, listNode1);
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stack1.push(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                stack2.push(l2.val);
                l2 = l2.next;
            }
        }
        int carry = 0;
        ListNode head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int s1;
            int s2;
            s1 = stack1.isEmpty() ? 0 : stack1.pop();
            s2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = s1 + s2 + carry;
            ListNode listNode = new ListNode(sum % 10);
            carry = sum / 10;
            listNode.next = head;
            head = listNode;
        }
        if (carry > 0) {
            ListNode listNode = new ListNode(1);
            listNode.next = head;
            head = listNode;
        }
        return head;
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
