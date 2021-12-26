package com.example.dynamicProgramming;

public class Leetcode_416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums) sum += num;
        if(sum % 2 != 0) return false;
//        return zero1pack(sum / 2, nums, nums.length - 1);
        return zero1pack(sum / 2, nums);
    }

//    // 01背包（暴力），[0, i] 随意取，能否恰好填满v
//    private boolean zero1pack(int v, int[] volume, int i) {
//        if(i < 0) return false;
//        if(v == 0) return true;
//        if(v < volume[i]) {
//            return zero1pack(v, volume, i - 1);
//        }
//        return zero1pack(v, volume, i - 1) ||
//                zero1pack(v - volume[i], volume, i - 1);
//    }

    // 01背包（动态规划）
    private boolean zero1pack(int v, int[] volume) {
        int n = volume.length;
        boolean[][] dp = new boolean[n][v+1];
        for(int j = 0; j <= v; j++) {
            dp[0][j] = (j == volume[0]) ? true : false;
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= v; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= volume[i]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - volume[i]];
                }
            }
        }
        return dp[n - 1][v];
    }

    // 记忆化搜索
//    public boolean canPartition(int[] nums) {
//        int s = 0;
//        for(int i : nums) {
//            s += i;
//        }
//        if(s % 2 != 0) {
//            return false;
//        }
//        s /= 2;
//        int[][] dp = new int[nums.length][s + 1];
//        return process(nums, 0, s, dp);
//    }
//    private boolean process(int[] nums, int i, int cur, int[][] dp) {
//        if(i >= nums.length) {
//            return cur == 0 ? true : false;
//        }
//        if(cur < 0) {
//            return false;
//        }
//        if(dp[i][cur] != 0) {
//            return dp[i][cur] == 1 ? true : false;
//        }
//        if(cur == 0) {
//            dp[i][cur] = 1;
//            return true;
//        }
//
//        boolean ans = process(nums, i + 1, cur - nums[i], dp) || process(nums, i + 1, cur, dp);
//        dp[i][cur] = ans ? 1 : 2;
//        return ans;
//    }
}
