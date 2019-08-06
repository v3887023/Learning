package com.example.learning.java;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode newLinkedList(int... values) {
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

    /**
     * 构造一条有环的链表
     *
     * @param nums     存放链表结点的数组
     * @param entrance 环的入口位置
     * @return 带环的链表
     */
    public static ListNode createCircularLinkedList(int[] nums, int entrance) {
        int length;
        if (nums == null || (length = nums.length) == 0) {
            throw new IllegalArgumentException("nums is null or is empty array");
        }

        if (entrance < 0 || entrance > length - 1) {
            throw new IllegalArgumentException("the entrance of circular linked list is invalid");
        }

        ListNode head = new ListNode(nums[0]);
        ListNode p = head;
        ListNode entranceNode = head;
        for (int i = 1; i < length; i++) {
            ListNode node = new ListNode(nums[i]);
            if (i == entrance) {
                entranceNode = node;
            }
            p.next = node;
            p = node;
        }

        p.next = entranceNode;

        return head;
    }
}
