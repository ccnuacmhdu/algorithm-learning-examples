package com.example.dynamicProgramming;

public class Leetcode_309 {
    // 冷冻期指第 i 天结束之后的状态。
    // 如果第 i 天结束之后处于冷冻期，第 i 天必持有 1 支股票并卖出，第 i+1 天无法买入股票。
    // dp[i][0]: 第 i 天结束，持有 1 支股票（第 i 天结束后肯定不在冷冻期），所得最大收益
    // dp[i][1]: 第 i 天结束，持有 0 支股票，且不在冷冻期（第 i 天必没有卖股票，因为卖了就处于冷冻期），所得最大收益
    // dp[i][2]: 第 i 天结束，持有 0 支股票，且在冷冻期（第 i - 1 天必持有股票且卖出），所得最大收益
    // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]))
    // dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]))
    // dp[i][2] = dp[i - 1][0] + prices[i]
    // dp[0][0] = -prices[0]
    // dp[0][1] = 0
    // dp[0][2] = 0
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][2] = dp[i - 1][0] + prices[i];
        }
        // 最后一天手中持有 1 支股票的话，显然是多花钱了(dp[n - 1][0])
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }
}
