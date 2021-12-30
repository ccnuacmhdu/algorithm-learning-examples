package com.example.hard;

import java.util.Arrays;

public class Leetcode_2122 {
    // nums 的最小元素 min 一定是 lower 数组的最小元素，枚举剩下的 nums[i]，并假定
    // nums[i] = min + 2 * k，逐次验证剩余最小元素是否都成立
    public int[] recoverArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n / 2];
        Arrays.sort(nums);
        for(int i = 1; i < n; i++) {
            // k2 就是 2 * k
            int k2 = nums[i] - nums[0];
            if(k2 == 0 || (k2 & 1) != 0) {
                continue;
            }
            int l = 0;
            int r = i;
            int idx = 0;
            boolean[] vis = new boolean[n];
            for(int j = 0; j < n / 2; j++) {
                while(l < n && vis[l]) {
                    l++;
                }
                while(r < n && (nums[r] - nums[l]) != k2) {
                    r++;
                }
                if(r == n) {
                    break;
                }
                ans[idx++] = nums[l] + k2 / 2;
                vis[r] = true;
                l++;
                r++;
            }
            if(idx == n / 2) {
                return ans;
            }
        }
        return new int[0];
    }
}
