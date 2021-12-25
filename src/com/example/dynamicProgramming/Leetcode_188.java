package com.example.dynamicProgramming;

public class Leetcode_188 {
    // dp[i][j][0]: 第 i 天结束，完成 j 笔交易，持有 0 支股票，所得最大收益（交易定义：卖出）
    // dp[i][j][1]: 第 i 天结束，完成 j 笔交易，持有 1 支股票，所得最大收益（交易定义：卖出）
    // dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i])
    // dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i])
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        k = Math.min(k, n / 2);
        int[][][] dp = new int[n][k + 1][2];
        // dp[0][j][0] = 0;
        for(int j = 0; j <= k; j++) {
            dp[0][j][1] = -prices[0];
        }
        // dp[i][0][0] = 0;
        for(int i = 1; i < n; i++) {
            dp[i][0][1] = Math.max(dp[i - 1][0][1], -prices[i]);
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i]);
            }
        }
        int ans = 0;
        for(int j = 0; j <= k; j++) {
            ans = Math.max(ans, dp[n - 1][j][0]);
        }
        return ans;
    }
}
