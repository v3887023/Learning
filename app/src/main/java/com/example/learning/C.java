package com.example.learning;

import com.example.learning.java.A;
import com.example.learning.java.Utils;

import java.util.LinkedList;

public class C extends A {
    public C() {

    }

    public static void main(String[] args) {
        Utils.println(new Solution().scoreOfParentheses("(()(()))"));
    }
}

class Solution {
    public int scoreOfParentheses(String S) {
        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Entry> scoreStack = new LinkedList<>();

        int level = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(level++);
            } else if (c == ')') {
                if (stack.size() > 0) {
                    int lv = stack.pop();

                    int size = scoreStack.size();
                    if (size == 0) {
                        scoreStack.push(new Entry(1, lv));
                        Utils.println("push: [1, " + lv + "]");
                    } else  {
                        Entry peek = scoreStack.peek();
                        if (peek.level == lv) {
                            peek.n++;
                            Utils.println("level: " + lv + ", plus one");
                        } else if (peek.level == lv + 1) {
                            peek.n *= 2;
                            peek.level--;
                            Utils.println("level: " + lv + ", times two");
                        } else {
                            scoreStack.push(new Entry(1, lv));
                            Utils.println("push: [1, " + lv + "]");
                        }
                    }
                }
                level--;
            }
        }


        return scoreStack.pop().n;
    }

    class Entry {
        int n;
        int level;

        Entry(int n, int level) {
            this.n = n;
            this.level = level;
        }
    }
}