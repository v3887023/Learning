package com.example.learning.java;

import java.util.Stack;

import static com.example.learning.java.Utils.println;

public class Test20 {
    public static void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//
//        node1.next = node2;
//
//        ListNode temp = node2.next;
//        node2.next = node1;
//        node1.next = null;
//
//
//        println(node1.next);
        int a[] = new int[]{9, 5, 4, 8, 9, 1};
        final int[] ints = calSubArray(a, 18);

        for (int i : ints) {
            Utils.println(i);
        }

        Stack<Character> stack = new Stack<>();
    }

    public static int[] calSubArray(int[] array, int num) {
        int length = array.length;
        int sum;
        int j;

        for(int i = 0; i < length; i++) {
            sum = 0;

            for(j = i; sum < num && j < length; j++) {
                sum += array[j];
            }

            if(sum == num) {
                return subArray(array, i, j);
            }
        }
        return null;
    }

    private static int[] subArray(int[] array, int start, int end) {
        int len = end - start;
        int[] a = new int[len];
        for(int i = 0; i < len; i++) {
            a[i] = array[start + i];
        }

        return a;
    }

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }
}
