package com.example.hashTable;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_1 {
    // 哈希表
    public int[] twoSum(int[] nums, int target) {
        if(nums ==  null || nums.length < 2) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }

    // 暴力法
//    public int[] twoSum(int[] nums, int target) {
//        if(nums ==  null || nums.length < 2) {
//            return new int[0];
//        }
//        for(int i = 0; i < nums.length; i++) {
//            for(int j = i + 1; j < nums.length; j++) {
//                if(nums[i] + nums[j] == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return new int[0];
//    }
}
