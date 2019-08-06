package com.example.learning.bcd;

import com.example.learning.java.Utils;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        Utils.println(new Solution().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS(int[] nums) {
        int length;
        if (nums == null || (length = nums.length) == 0) {
            return 0;
        }

        int[] dp = new int[length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
        }

        int max = Integer.MIN_VALUE;
        for (int n : dp) {
            if (max < n) {
                max = n;
            }
        }

        return max;
    }
}