package com.example.dynamicProgramming;

public class Leetcode_918 {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        max = Math.max(max, getMax(nums));
        int sum = 0;
        boolean allNeg = true;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            if(nums[i] >= 0) allNeg = false;
        }
        // 数组全负，-3 -2 -3, sum = -8, min = -8, max = sum - min = 0, 但真正最大值应该是 -2 啊！
        if(allNeg) {
            return max;
        }
        max = Math.max(max, sum - getMin(nums));
        return max;
    }
    // 最小子数组和（环形数组最大子数组和 = 数组和 - 最小子数组和，但要考虑数组全负数情况）
    private int getMin(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        int min = a[0];
        dp[0] = a[0];
        for(int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] > 0 ? a[i] : (dp[i - 1] + a[i]);
            min = Math.min(dp[i], min);
        }
        return min;
    }
    // 最大子数组和
    private int getMax(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        int max = a[0];
        dp[0] = a[0];
        for(int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] < 0 ? a[i] : (dp[i - 1] + a[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
