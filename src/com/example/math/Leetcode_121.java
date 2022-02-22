package com.example.math;

import java.util.Stack;

public class Leetcode_121 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        int buy = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - buy);
            if(prices[i] < buy) {
                buy = prices[i];
            }
        }
        return maxProfit;
    }

    // 单调栈或前缀数组优化
//    public int maxProfit(int[] prices) {
//        int minPrice = Integer.MAX_VALUE;
//        int ans = 0;
//        for(int price : prices) {
//            if(price > minPrice) {
//                ans = Math.max(ans, price - minPrice);
//            }
//            minPrice = Math.min(minPrice, price);
//        }
//        return ans;
//    }

    // 单调栈
//    public int maxProfit(int[] prices) {
//        Stack<Integer> stack = new Stack<>();
//        int ans = 0;
//        for(int price : prices) {
//            if(stack.isEmpty() || price < stack.peek()) {
//                stack.push(price);
//            } else {
//                ans = Math.max(ans, price - stack.peek());
//            }
//        }
//        return ans;
//    }

    // 前缀数组
//    public int maxProfit(int[] prices) {
//        if(prices == null || prices.length < 2) {
//            return 0;
//        }
//        int n = prices.length;
//
//        int[] prefix = new int[n];
//        prefix[0] = prices[0];
//        for(int i = 1; i < n; i++) {
//            prefix[i] = Math.min(prefix[i - 1], prices[i]);
//        }
//
//        int profit = 0;
//        for(int i = 1; i < n; i++) {
//            profit = Math.max(profit, prices[i] - prefix[i - 1]);
//        }
//        return profit;
//    }

    // 前缀数组优化
//    public int maxProfit(int[] prices) {
//        if(prices == null || prices.length < 2) {
//            return 0;
//        }
//        int profit = 0;
//        int n = prices.length;
//        int minPrice = prices[0];
//        for(int i = 1; i < n; i++) {
//            profit = Math.max(profit, prices[i] - minPrice);
//            minPrice = Math.min(minPrice, prices[i]);
//        }
//        return profit;
//    }

    // 后缀数组
//    public int maxProfit(int[] prices) {
//        if(prices == null || prices.length < 2) {
//            return 0;
//        }
//        int n = prices.length;
//
//        int[] suffix = new int[n];
//        suffix[n - 1] = prices[n - 1];
//        for(int i = n - 2; i >= 0; i--) {
//            suffix[i] = Math.max(suffix[i + 1], prices[i]);
//        }
//
//        int profit = 0;
//        for(int i = 0; i < n - 1; i++) {
//            profit = Math.max(profit, suffix[i + 1] - prices[i]);
//        }
//        return profit;
//    }

    // 后缀数组优化
//    public int maxProfit(int[] prices) {
//        if(prices == null || prices.length < 2) {
//            return 0;
//        }
//        int profit = 0;
//        int n = prices.length;
//        int maxPrice = prices[n - 1];
//        for(int i = n - 2; i >= 0; i--) {
//            profit = Math.max(profit, maxPrice - prices[i]);
//            maxPrice = Math.max(maxPrice, prices[i]);
//        }
//        return profit;
//    }
}
