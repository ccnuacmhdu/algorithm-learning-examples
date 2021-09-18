package com.example.leetcode100hot;

import java.util.Arrays;

public class Leetcode_128 {
    public int longestConsecutive(int[] nums) {
        if(nums.length < 2) {
            return nums.length;
        }
        Arrays.sort(nums);
        int tmpLen = 1;
        int maxLen = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]) {
                continue;
            }
            if(nums[i] == nums[i-1] + 1) {
                tmpLen++;
            } else {
                maxLen = Math.max(tmpLen, maxLen);
                tmpLen = 1;
            }
        }
        maxLen = Math.max(maxLen, tmpLen);
        return maxLen;
    }
}
