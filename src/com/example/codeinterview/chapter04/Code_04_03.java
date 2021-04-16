package com.example.codeinterview.chapter04;

import java.util.Arrays;

// [leetcode] 322. 零钱兑换
public class Code_04_03 {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount+1];
        for(int i = 0; i < coins.length; i++) {
            Arrays.fill(dp[i], -2);
        }
        return completeBackpack(coins, coins.length - 1,amount, dp);
    }
    // 【记忆化搜索】（完全背包）思路：利用前n种硬币，凑成target，所需要的最小硬币数
    private int completeBackpack(int[] value, int n, int target, int[][] dp) {
        if(n == -1) {
            return target == 0 ? 0 : -1;
        }
        if(dp[n][target] != -2) {
            return dp[n][target];
        }
        int ans = -1;
        for(int i = 0; i <= target / value[n]; i++) {
            int next = completeBackpack(value, n - 1,target - i*value[n], dp);
            if(next != -1) {
                ans = ans == -1 ? next + i : Math.min(ans, next + i);
            }
        }
        return dp[n][target] = ans;
    }

    // 【动态规划】
    public int coinChange2(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount+1];
        dp[0][0] = 0;
        for(int i = 1; i <= amount; i++) {
            dp[0][i] = i % coins[0] == 0 ? i / coins[0] : -1;
        }
        for(int i = 1; i < coins.length; i++) {
            dp[i][0] = 0;
            for(int j = 1; j <= amount; j++) {
                int ans = -1;
                for(int k = 0; k <= j / coins[i]; k++) {
                    int pre = dp[i-1][j - k * coins[i]];
                    if(pre != -1) {
                        ans = ans == -1 ? pre + k : Math.min(ans, pre + k);
                    }
                }
                dp[i][j] = ans;
            }
        }
        return dp[coins.length-1][amount];
    }

    // 【动态规划（状态压缩）】
    public int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for(int i = 1; i <= amount; i++) {
            dp[i] = i % coins[0] == 0 ? i / coins[0] : -1;
        }
        for(int i = 1; i < coins.length; i++) {
            for(int j = 1; j <= amount; j++) {
                int ans = -1;
                for(int k = 0; k <= j / coins[i]; k++) {
                    int pre = dp[j - k * coins[i]];
                    if(pre != -1) {
                        ans = ans == -1 ? pre + k : Math.min(ans, pre + k);
                    }
                }
                dp[j] = ans;
            }
        }
        return dp[amount];
    }
}
