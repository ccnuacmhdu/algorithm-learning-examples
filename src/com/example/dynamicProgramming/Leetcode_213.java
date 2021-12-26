package com.example.dynamicProgramming;

public class Leetcode_213 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        if(n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 如果打劫 0 号房间，打劫范围就是 [0, n - 2]；
        // 如果不打劫 0 号房间，打劫范围就是 [1, n - 1]
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    // 198. 打家劫舍
    public int rob(int[] nums, int st, int en) {
        int a = 0, b = nums[st];
        int ta, tb;
        for(int i = st + 1; i <= en; i++) {
            ta = Math.max(b, a);
            tb = a + nums[i];
            a = ta;
            b = tb;
        }
        return Math.max(a, b);
    }
}
