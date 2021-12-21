package com.example.dynamicProgramming;

public class Leetcode_152 {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] min = new int[n];
        int[] max = new int[n];
        min[0] = max[0] = nums[0];
        int res = nums[0];
        for(int i = 1; i < n; i++) {
            if(nums[i] >= 0) {
                max[i] = Math.max(max[i-1] * nums[i], nums[i]);
                min[i] = Math.min(min[i-1] * nums[i], nums[i]);
            } else {
                max[i] = Math.max(min[i-1] * nums[i], nums[i]);
                min[i] = Math.min(max[i-1] * nums[i], nums[i]);
            }
            res = Math.max(res, max[i]);
        }
        return res;
    }
}
