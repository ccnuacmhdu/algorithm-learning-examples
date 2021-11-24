package com.example.leetcode100hot;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_1 {
    // 暴力法
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
    // 哈希表
//    public int[] twoSum(int[] nums, int target) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int i = 0; i < nums.length; i++) {
//            if(map.containsKey(target - nums[i])) {
//                return new int[]{map.get(target - nums[i]), i};
//            }
//            map.put(nums[i], i);
//        }
//        return new int[0];
//    }
}
