package com.example.codeinterview.chapter04;

// [leetcode] 300. 最长递增子序列
public class Code_04_06 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int ans = 1;
        for(int i = 1; i < n; i++) {
            int len = 1;
            for(int j = i - 1; j >= 0; j--) {
                len = Math.max(len, nums[i] > nums[j] ? dp[j] + 1 : 1);
            }
            dp[i] = len;
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}
