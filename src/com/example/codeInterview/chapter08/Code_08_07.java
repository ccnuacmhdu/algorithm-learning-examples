package com.example.codeInterview.chapter08;

// [leetcode] 剑指 Offer 42. 连续子数组的最大和
public class Code_08_07 {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for(int i = 0; i < nums.length; i++) {
            cur += nums[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }
}
