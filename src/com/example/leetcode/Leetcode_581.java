package com.example.leetcode;

import java.util.Arrays;

public class Leetcode_581 {
    public int findUnsortedSubarray(int[] nums) {
        int[] sort = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sort);
        int from = 1, to = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != sort[i]) {
                from = i;
                break;
            }
        }
        for(int i = nums.length - 1; i > from; i--) {
            if(nums[i] != sort[i]) {
                to = i;
                break;
            }
        }
        return to - from + 1;
    }

    public static void main(String[] args) {
        int[] nums = {2,6,4,8,10,9,15};
        int res = new Leetcode_581().findUnsortedSubarray(nums);
        System.out.println(res);
    }
}
