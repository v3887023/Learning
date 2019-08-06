package com.example.learning.java;

public class Test27 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[] chars = new char[]{'a', 'b', 'c'};
        final int length = solution.compress(chars);

        for (int i = 0; i < length; i++) {
            Utils.print(chars[i]);
        }
    }

    static class Solution {
        public int compress(char[] chars) {
            int length = chars.length;

            int start = 0;

            char c;
            char lastC = chars[0];
            int count = 0;
            int len;
            int cCount;
            for (int i = 1; i <= length; i++) {
                if (i == length) {
                    c = '\0';
                } else {
                    c = chars[i];
                }

                if (c != lastC) {
                    chars[count] = lastC;
                    count++;
                    cCount = i - start;
                    len = intLength(cCount);
                    if (cCount != 1) {
                        for (int j = count + len; j >= count; j--) {
                            chars[j] = (char) (cCount % 10 + '0');
                            cCount /= 10;
                        }
                        count += len + 1;
                    }

                    start = i;
                    lastC = c;
                }
            }

            return count;
        }

        private int intLength(int n) {
            int length = 0;

            do {
                length++;
                n /= 10;
            } while (n > 0);

            return length;
        }
    }
}
