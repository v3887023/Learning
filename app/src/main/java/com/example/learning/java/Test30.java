package com.example.learning.java;

import java.util.LinkedList;

public class Test30 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        Utils.println(minStack.getMin());
        minStack.pop();
        Utils.println(minStack.top());
        Utils.println(minStack.getMin());
    }

    static class MinStack {
        private LinkedList<Integer> stack;
        private Node head;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new LinkedList<>();
        }

        public void push(int x) {
            stack.push(x);

            if (head == null) {
                head = new Node(x);
                return;
            }

            if (x < head.val) {
                Node node = new Node(x);
                node.next = head;
                head = node;
                return;
            }

            Node pre = head;
            Node p = pre.next;
            while (p != null) {
                if (x < p.val) {
                    break;
                }
                pre = p;
                p = p.next;
            }

            Node node = new Node(x);
            node.next = pre.next;
            pre.next = node;
        }

        public void pop() {
            int x = stack.pop();

            if (head.val == x) {
                Node next = head.next;
                if (next == null) {
                    head = null;
                } else {
                    head.next = null;
                    head = next;
                }
            } else {
                Node pre = head;
                Node p = pre.next;
                while (p != null) {
                    if (p.val == x) {
                        break;
                    }
                    pre = p;
                    p = p.next;
                }

                pre.next = p.next;
                p.next = null;
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return head.val;
        }

        private class Node {
            int val;
            Node next;

            Node(int val) {
                this.val = val;
            }
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}