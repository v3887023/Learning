package com.example.learning.java;

public class Test33 {
    public static void main(String[] args) {
        ListNode list = new Solution3().insertionSortList(ListNode.newLinkedList(3, 1, 4, 2, 0, 5, -1, 4));

        Utils.printLinkedList(list);
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution3 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newHead = new ListNode(head.val);

        ListNode p = head.next;
        ListNode q;
        int val;
        ListNode pre;
        while (p != null) {
            val = p.val;
            ListNode node = new ListNode(val);

            if (val < newHead.val) {
                node.next = newHead;
                newHead = node;
            } else {
                pre = null;
                q = newHead;
                while (q != null && q != p) {
                    if (q.val > val) {
                        node.next = q;
                        pre.next = node;
                        break;
                    }

                    pre = q;
                    q = q.next;
                }

                if (q == null) {
                    pre.next = node;
                }
            }

            p = p.next;
        }

        return newHead;
    }
}
