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
//        if(v == volume[i]) return true;
//        if(v < volume[i]) {
//            return zero1pack(v, volume, i - 1);
//        }
//        return zero1pack(v, volume, i - 1) ||
//                zero1pack(v - volume[i], volume, i - 1);
//    }

    // 01背包（动态规划）
    private boolean zero1pack(int v, int[] volume) {
        int n = volume.length;
        boolean[][] dp = new boolean[v+1][n];
        for(int i = 1; i <= v; i++) {
            dp[i][0] = (volume[0] == v);
            for(int j = 1; j < n; j++) {
                if(i == volume[j]) {
                    dp[i][j] = true;
                } else if(i < volume[j]) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    dp[i][j] = dp[i][j-1] || dp[i-volume[j]][j-1];
                }
            }
        }
        return dp[v][n-1];
    }

}
