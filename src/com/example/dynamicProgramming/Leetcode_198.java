package com.example.dynamicProgramming;

public class Leetcode_198 {
    // dp[i][0]: 从第 0 间到第 i 间依次打劫，不打劫第 i 间房，所获最大收益
    // dp[i][1]: 从第 0 间到第 i 间依次打劫，打劫第 i 间房，所获最大收益
    // dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0])
    // dp[i][1] = dp[i - 1][0] + nums[i]
    public int rob(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][1] = nums[0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    // 状态压缩
//    public int rob(int[] nums) {
//        int n = nums.length;
//        int a = 0, b = nums[0];
//        int ta, tb;
//        for(int i = 1; i < n; i++) {
//            ta = Math.max(b, a);
//            tb = a + nums[i];
//            a = ta;
//            b = tb;
//        }
//        return Math.max(a, b);
//    }
}
