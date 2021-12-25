package com.example.dynamicProgramming;

public class Leetcode_122 {
    // dp[i][0]: 第 i 天结束持有 0 支股票的最大收益
    // dp[i][1]: 第 i 天结束持有 1 支股票的最大收益
    // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
    // dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i])
    // dp[0][0] = 0, dp[0][1] = -prices[0]
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

//    public int maxProfit(int[] prices) {
//        if(prices == null || prices.length < 2) {
//            return 0;
//        }
//        int n = prices.length;
//        int a = 0, b = -prices[0];
//        int ta, tb;
//        for(int i = 1; i < n; i++) {
//            ta = Math.max(a, b + prices[i]);    // 用 ?: 代替 Math.max 可以加速
//            tb = Math.max(b, a - prices[i]);
//            a = ta;
//            b = tb;
//        }
//        return a;
//    }

    // 贪心，例子 1 2 3 的利润 = 3 - 1 = (2 - 1) + (3 - 2)，后种算法累加贡献度，与前种算法效果一样
//    public int maxProfit(int[] prices) {
//        int ans = 0;
//        for(int i = 1; i < prices.length; i++) {
//            int profit = prices[i] - prices[i - 1];
//            if(profit > 0) {
//                ans += profit;
//            }
//        }
//        return ans;
//    }
}
