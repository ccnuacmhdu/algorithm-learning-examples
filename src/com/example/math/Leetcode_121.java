package com.example.math;

public class Leetcode_121 {
    // 数学
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                max = Math.max(prices[i] - min, max);
            }
        }
        return max;
    }

    // 预处理
//    public int maxProfit(int[] prices) {
//        if(prices == null || prices.length < 2) return 0;
//        int n = prices.length;
//        int[] big = new int[n];
//        big[n-1] = prices[n-1];
//        for(int i = n - 2; i >= 0; i--) {
//            big[i] = Math.max(big[i+1], prices[i]);
//        }
//        int max = 0;
//        for(int i = 0; i < n - 1; i++) {
//            max = Math.max(max, big[i+1] - prices[i]);
//        }
//        return max;
//    }
}
