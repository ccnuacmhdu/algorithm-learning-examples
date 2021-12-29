package com.example.greedy;

import java.util.Arrays;

public class Leetcode_581 {
    // 思维
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int r = -1;
        int l = -1;
        for(int i = 0; i < n; i++) {
            if(nums[i] >= max) {
                max = nums[i];
            } else {
                r = i;
            }
        }
        if(r == -1) {
            return 0;
        }
        for(int i = n - 1; i >= 0; i--) {
            if(nums[i] <= min) {
                min = nums[i];
            } else {
                l = i;
            }
        }
        return r - l + 1;
    }

    // 常规法
//    public int findUnsortedSubarray(int[] nums) {
//        int n = nums.length;
//        int[] copy = Arrays.copyOfRange(nums, 0, n);
//        Arrays.sort(copy);
//        int i = 0;
//        int j = n - 1;
//        while(i < n && nums[i] == copy[i]) {
//            i++;
//        }
//        if(i == n) return 0;
//        while(j >= 0 && nums[j] == copy[j]) {
//            j--;
//        }
//        return j - i + 1;
//    }
}
