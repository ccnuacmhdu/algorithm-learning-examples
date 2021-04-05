package com.example.leetcode.lccup21;

import java.util.Arrays;

/**
 * 采购方案
 */
public class Problem01 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 9};
        int target = 10;
        int res = purchasePlans(nums, target);
        System.out.println(res);
    }

    public static int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        long res = 0L;
        for(int i = 0; i < nums.length - 1; i++) {
            int index = getMaxIndexLessOrEqual(nums, target - nums[i], i + 1, nums.length - 1);
            if(index == -1) {
                continue;
            }
            res = (res % 1000000007) + (index - i) % 1000000007;
        }
        return (int)(res % 1000000007);
    }

    public static int getMaxIndexLessOrEqual(int[] a, int v, int L, int R){
        if(L > R){
            return -1;
        }
        int left = L;
        int right = R;
        int index = -1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if(a[mid] <= v){
                index = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return index;
    }
}
