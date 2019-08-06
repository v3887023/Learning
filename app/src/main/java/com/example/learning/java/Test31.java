package com.example.learning.java;

import java.util.LinkedList;

public class Test31 {
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
}

class MinStack {
    private LinkedList<Node> stack;
    private int min = Integer.MAX_VALUE;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        if (min > x) {
            min = x;
        }
        stack.push(new Node(x, min));
    }

    public void pop() {
        stack.pop();
        min = stack.size() == 0 ? Integer.MAX_VALUE : getMin();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return min;
    }

    private class Node {
        int val;
        int min;

        Node(int val, int min) {
            this.val = val;
            this.min = min;
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