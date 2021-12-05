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
        int[][] dp = new int[amount+1][n];
        int MAX_VALUE = 10001;

        for(int i = 1; i <= amount; i++) {
            dp[i][0] = (i % coins[0] == 0) ? (i / coins[0]) : MAX_VALUE;
        }
        for(int i = 1; i <= amount; i++) {
            for(int j = 1; j < n; j++) {
                int ans = MAX_VALUE;
                for(int k = 0; k * coins[j] <= i; k++) {
                    ans = Math.min(ans, dp[i-k*coins[j]][j-1] + k);
                }
                dp[i][j] = ans;
            }
        }
        return dp[amount][n-1] >= MAX_VALUE ? -1 : dp[amount][n-1];
    }
}
