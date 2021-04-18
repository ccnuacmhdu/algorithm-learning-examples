package com.example.codeinterview.chapter04;

// [leetcode] 45. 跳跃游戏 II
public class Code_04_13 {
    public int jump(int[] nums) {
        // 暴力递归
        // return process(nums, 0);

        // 动态规划
//        int n = nums.length;
//        int[] dp = new int[n];
//        dp[n-1] = 0;
//        for(int i = n - 2; i >= 0; i--) {
//            int times = 10000000;
//            for(int j = 1; j <= nums[i]; j++) {
//                if(i+j >= nums.length - 1) {
//                    times = 1;
//                    break;
//                }
//                if(nums[i+j] != 0) {
//                    times = Math.min(times, 1 + dp[i+j]);
//                }
//            }
//            dp[i] = times;
//        }
//        return dp[0];

        // 贪心
        int maxPos = 0;
        int curEnd = 0;
        int step = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if(i == curEnd) {
                curEnd = maxPos;
                step++;
            }
        }
        return step;
    }
    private int process(int[] nums, int i) {
        if(i >= nums.length - 1) {
            return 0;
        }
        int times = 10000000;
        for(int j = 1; j <= nums[i]; j++) {
            if(i+j >= nums.length - 1) {
                times = 1;
                break;
            }
            if(nums[i+j] != 0) {
                times = Math.min(times, 1 + process(nums, i+j));
            }
        }
        return times;
    }
}
