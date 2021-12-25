package com.example.math;

public class Leetcode_121 {
    public int maxProfit(int[] prices) {
        int leftMin = Integer.MAX_VALUE;
        int ans = 0;
        for(int i = 0; i < prices.length; i++) {
            if(prices[i] > leftMin) {
                ans = Math.max(ans, prices[i] - leftMin);
            }
            leftMin = prices[i] < leftMin ? prices[i] : leftMin;
        }
        return ans;
    }
}
