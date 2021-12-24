package com.example.prefixSum;

public class Leetcode_238 {
    // 空间复杂度: O(1)，时间复杂度: O(n)
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] out = new int[n];
        out[0] = 1;
        for (int i = 1; i < n; i++) {
            out[i] = out[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 2; i > 0; i--) {
            right *= nums[i + 1];
            out[i] = out[i] * right;
        }
        out[0] = right * nums[1];
        return out;
    }

    // 空间复杂度: O(n)，时间复杂度: O(n)
//    public int[] productExceptSelf(int[] nums) {
//        if(nums == null || nums.length == 0) {
//            return new int[0];
//        }
//        int n = nums.length;
//        int[] out = new int[n];
//        int[] left = new int[n];
//        int[] right = new int[n];
//        left[0] = right[n - 1] = 1;
//        for(int i = 1; i < n; i++) {
//            left[i] = left[i - 1] * nums[i - 1];
//        }
//        for(int i = n - 2; i >= 0; i--) {
//            right[i] = right[i + 1] * nums[i + 1];
//        }
//        for(int i = 0; i < n; i++) {
//            out[i] = left[i] * right[i];
//        }
//        return out;
//    }
}
