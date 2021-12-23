package com.example.leetcode;

public class Leetcode_238 {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        for(int i = 1; i < nums.length; i++) {
            output[i] = i == 1 ? output[1] = nums[0] : output[i-1] * nums[i-1];
        }
        int c = 1;
        for(int i = nums.length - 2; i >= 0; i--) {
            c *= nums[i+1];
            output[i] = i == 0 ? c : output[i] * c;
        }
        return output;
    }
}
