package com.example.learning.abc;

import com.example.learning.java.ListNode;

public class Solution {
    public static void main(String[] args) {
        ListNode list = ListNode.createCircularLinkedList(new int[]{1, 2, 3, 4, 5, 6}, 5);

        Solution solution = new Solution();
        System.out.println(solution.getCircularLinkedListEntrance(list).val);
    }

    public ListNode getCircularLinkedListEntrance(ListNode list) {
        ListNode fast = list;
        ListNode slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 快慢指针相遇时退出循环
            if (fast == slow) {
                break;
            }
        }

        // 该链表没有环
        if (fast == null) {
            return null;
        }

        // 慢指针指向头结点
        slow = list;

        // 快慢指针同时头，相遇时的节点就是要求的环的入口结点了
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }
}

