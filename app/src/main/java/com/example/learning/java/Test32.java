package com.example.learning.java;

import java.util.ArrayList;
import java.util.List;

public class Test32 {
    public static void main(String[] args) {
        String[] a = {"Hello", "Alaska", "Dad", "Peace"};
        String[] words = new Solution2().findWords(a);
        for (String s : words) {
            Utils.println(s);
        }
    }
}

class Solution2 {
    private static final String[] keyboard = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};

    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>(words.length);

        int row;
        int length;
        boolean find;
        for (String word : words) {
            row = row(word.charAt(0));

            length = word.length();
            find = true;
            for (int i = 1; i < length; i++) {
                if (!contains(keyboard[row], word.charAt(i))) {
                    find = false;
                }
            }

            if (find) {
                list.add(word);
            }
        }

        String[] result = new String[list.size()];
        result = list.toArray(result);

        return result;
    }

    private boolean contains(String s, char target) {
        int length = s.length();
        char c;
        for (int i = 0; i < length; i++) {
            c = s.charAt(i);
            if (c == target || c == target -'a' + 'A') {
                return true;
            }
        }

        return false;
    }

    private int row(char c) {
        int row;
        for (row = 0; row < 3; row++) {
            if (contains(keyboard[row], c)) {
                return row;
            }
        }

        return -1;
    }
}