package com.example.learning.java;

import static com.example.learning.java.Utils.print;
import static com.example.learning.java.Utils.println;

/**
 * 反转单链表
 */
public class LinkedListTest {
    public static void main(String[] args) {
        ListNode list = newLinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        printLinkedList(list);

        ListNode head = reverseLinkedList(list);

        printLinkedList(head);

        ListNode listA = newLinkedList(3, 8, 11, 13, 15, 100, 1000, 1024);
        ListNode listB = newLinkedList(-2, 2, 9, 10, 99);

        println();
        print("list A: ");
        printLinkedList(listA);
        print("list B: ");
        printLinkedList(listB);
        ListNode newHead = merge(listA, listB);
        print("merged list: ");
        printLinkedList(newHead);

        ListNode node = newLinkedList(1, 1, 1, 2, 2, 3, 3, 3);
        print("list before: ");
        printLinkedList(node);
        print("list after:  ");
        printLinkedList(deleteDuplicates(node));
    }

    private static ListNode merge(ListNode pA, ListNode pB) {
        if (pA == null && pB == null) {
            return null;
        } else if (pA == null) {
            return pB;
        } else if (pB == null) {
            return pA;
        }

        ListNode head;
        ListNode p;

        if (pA.val <= pB.val) {
            p = pA;
            pA = pA.next;
        } else {
            p = pB;
            pB = pB.next;
        }

        p.next = null;
        head = p;

        while (pA != null && pB != null) {
            if (pA.val <= pB.val) {
                p.next = pA;
                pA = pA.next;
            } else {
                p.next = pB;
                pB = pB.next;
            }
            p = p.next;
            p.next = null;
        }

        if (pA == null) {
            p.next = pB;
        }

        if (pB == null) {
            p.next = pA;
        }

        return head;
    }

    private static ListNode reverseLinkedList(ListNode list) {
        ListNode head = null;
        ListNode pre;
        ListNode p = list;

        while (p != null) {
            pre = p.next;
            p.next = head;
            head = p;
            p = pre;
        }

        return head;
    }

    private static ListNode newLinkedList(int... values) {
        int length = values.length;
        if (length <= 0) {
            return null;
        }

        ListNode head = new ListNode(values[0], null);
        ListNode p = head;
        ListNode node;
        for (int i = 1; i < length; i++) {
            node = new ListNode(values[i], null);
            p.next = node;
            p = p.next;
        }

        return head;
    }

    private static void printLinkedList(ListNode head) {
        ListNode p = head;
        while (p != null) {
            print(p.val);
            print(" -> ");
            p = p.next;
        }
        println("NULL");
    }

    private static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        int last = head.val;

        ListNode p = head;
        ListNode next = p.next;
        int current;
        while (next != null) {
            current = next.val;
            if (current != last) {
                last = current;
                p.next = next;
                p = next;
            }

            next = next.next;
        }

        p.next = null;

        return head;
    }
}
