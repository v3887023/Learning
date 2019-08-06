package com.example.learning.def;

import com.example.learning.java.Utils;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5, 10, 20, 50, 100};
        int amount = 6249;
        Utils.println(new Solution().coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;

        // dp[i] 表示最少能用 dp[i] 个硬币得到数额 i
        int[] dp = new int[max];
        Arrays.fill(dp, max);   // max 的值表示无法兑换零钱
        dp[0] = 0;

        for (int i = 1; i < max; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }

        return dp[amount] < max ? dp[amount] : -1;
    }
}
