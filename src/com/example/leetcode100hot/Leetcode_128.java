package com.example.leetcode100hot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int maxLen = 0;
        for(int i: set) {
            if(!set.contains(i-1)) {
                int num = i+1;
                int tmpLen = 1;
                while (set.contains(num)) {
                    tmpLen++;
                    num++;
                }
                maxLen = Math.max(maxLen, tmpLen);
            }
        }
        return maxLen;
    }
}
