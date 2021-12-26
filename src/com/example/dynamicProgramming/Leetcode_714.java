package com.example.dynamicProgramming;

public class Leetcode_714 {

    // dp[i][0]: 第 i 天结束，持有 0 支股票，所得最大收益
    // dp[i][1]: 第 i 天结束，持有 1 支股票，所得最大收益
    // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee)
    // dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i])
    // dp[0][0] = 0
    // dp[0][1] = -prices[0]
    public int maxProfit(int[] prices, int fee) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    // 贪心
//    public int maxProfit(int[] prices, int fee) {
//        if(prices == null || prices.length < 2) {
//            return 0;
//        }
//        int n = prices.length;
//        int maxProfit = 0;
//        int buy = prices[0] + fee;
//        for(int i = 1; i < n; i++) {
//            if(prices[i] + fee < buy) {
//                // 价格越来越低了，不能卖，而是更新买时价格
//                buy = prices[i] + fee;
//            } else if(prices[i] > buy){
//                // 有利润了，卖了，之后如果一直可以卖的话，不用再次付 fee，因为这里贪心算法多次买卖次数其实
//                // 不是真正的交易次数，是不断地比较相邻两个价格，决定卖出还是更新到更小的买入价格
//                maxProfit += prices[i] - buy;
//                buy = prices[i];
//            }
//        }
//        return maxProfit;
//    }
}
