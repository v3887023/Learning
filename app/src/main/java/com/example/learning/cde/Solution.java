package com.example.learning.cde;

import com.example.learning.java.Utils;

public class Solution {
    public static void main(String[] args) {
        Utils.println(new Solution().longestPalindrome("babab"));
    }

    public String longestPalindrome(String s) {
        int length = s.length();

        boolean[][] dp = new boolean[length][length];
        String result = s.substring(0, 1);
        for (int j = 0; j < length; j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 1 || dp[i + 1][j - 1]);

                if (dp[i][j] && j - i + 1 > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }
}
