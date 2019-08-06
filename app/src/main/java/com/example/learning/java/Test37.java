package com.example.learning.java;

public class Test37 {
    public static void main(String[] args) {
        Utils.println(new Solution().reverseWords("  hello world!  "));
    }
}

class Solution {
    public String reverseWords(String s) {
        int length = s.length();

        int end = length - 1;
        StringBuilder sb = new StringBuilder();
        char[] array = s.toCharArray();
        int count;
        for (int i = end; i >= 0; i--) {
            if (array[i] == ' ') {
                count = end - i;
                if (count > 0) {
                    sb.append(array, i + 1, count);
                    while (i >= 0 && array[i] == ' ') {
                        i--;
                    }
                    if (i > 0) {
                        sb.append(' ');
                    }
                }
                end = i;
            }
        }

        if (end >= 0) {
            sb.append(array, 0, end + 1);
        }

        return sb.toString();
    }
}