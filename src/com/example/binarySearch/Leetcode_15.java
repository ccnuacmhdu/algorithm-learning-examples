package com.example.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if(nums == null || nums.length < 3) {
            return lists;
        }
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i + 1; j < n; j++) {
                if(j > i + 1 && nums[j] == nums[j-1]) continue;
                int t = 0 - (nums[i] + nums[j]);
                int pos = binary_search(nums, t, j + 1, n - 1);
                if(pos != -1) {
                    lists.add(Arrays.asList(nums[i], nums[j], nums[pos]));
                }
            }
        }
        return lists;
    }
    private int binary_search(int[] nums, int v, int l, int r) {
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if(nums[mid] == v) {
                return mid;
            } else if(nums[mid] > v) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}
