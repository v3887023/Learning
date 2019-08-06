package com.example.learning.java;

public class Test22 {
    public static void main(String[] args) {
        removeElements(new ListNode(1), 1);
    }

    private static ListNode removeElements(ListNode head, int val) {
        ListNode p = head;
        ListNode pre = null;
        ListNode target;
        ListNode next;
        while (p != null) {
            if (p.val == val) {
                target = p;
                next = target.next;
                if (next == null) {
                    if (pre == null) {
                        head = null;
                        break;
                    } else {
                        pre.next = null;
                        break;
                    }
                } else {
                    target.val = next.val;
                    target.next = next.next;
                    next.next = null;
                }
            } else {
                pre = p;
                p = p.next;
            }
        }

        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
