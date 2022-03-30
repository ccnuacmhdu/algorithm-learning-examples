package com.example.dynamicProgramming;

import java.util.Arrays;

public class Leetcode_322 {
    // 完全背包（记忆化搜索）
//    public int coinChange(int[] coins, int amount) {
//        int n = coins.length;
//        int[][] dp = new int[amount+1][n];
//        int MAX_VALUE = 10001;
//        for(int i = 0; i <= amount; i++) {
//            Arrays.fill(dp[i], -1);
//        }
//        int res = completePack(amount, coins, n - 1, dp, MAX_VALUE);
//        return res == MAX_VALUE ? -1 : res;
//    }
//    private int completePack(int v, int[] volume, int i, int[][] dp, int max) {
//        if(i == -1) return v == 0 ? 0 : max;
//        if(v == 0) return 0;
//
//        if(dp[v][i] != -1) return dp[v][i];
//
//        int ans = max;
//        for(int k = 0; k * volume[i] <= v; k++) {
//            ans = Math.min(ans, completePack(v - k * volume[i], volume, i - 1, dp, max) + k);
//        }
//        return dp[v][i] = ans;
//    }

    // 完全背包（动态规划）
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        int MAX_VALUE = 10001;

        for(int v = 1; v <= amount; v++) {
            dp[0][v] = (v % coins[0] == 0) ? v / coins[0] : MAX_VALUE;
        }
        int min_coins = dp[0][amount];
        for(int i = 1; i < n; i++) {
            for(int v = 1; v <= amount; v++) {
                dp[i][v] = dp[i - 1][v];
                for(int k = 0; k * coins[i] <= v; k++) {
                    dp[i][v] = Math.min(dp[i][v], dp[i - 1][v - k * coins[i]] + k);
                }
            }
            min_coins = Math.min(min_coins, dp[i][amount]);
        }
        return min_coins >= MAX_VALUE ? -1 : min_coins;
    }
}
