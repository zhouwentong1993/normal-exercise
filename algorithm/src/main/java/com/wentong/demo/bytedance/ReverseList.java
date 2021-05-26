package com.wentong.demo.bytedance;

public class ReverseList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverseList(listNode);
    }

    public static ListNode reverseList(ListNode head) {

        ListNode start = head;
        while (head.next != null) {
            ListNode temp = head.next.next;
            head.next.next = start;
            head.next = temp;
            head = head.next;
            System.out.println(head.val);
        }
        return start;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
