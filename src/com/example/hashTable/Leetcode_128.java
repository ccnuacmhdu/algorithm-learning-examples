package com.example.hashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Leetcode_128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i : nums) {
            set.add(i);
        }
        int res = 0;
        for(int i : set) {
            if(!set.contains(i - 1)) {
                int x = i + 1;
                int len = 1;
                while(set.contains(x++)) {
                    len++;
                }
                res = Math.max(res, len);
            }
        }
        return res;
    }

//    public int longestConsecutive(int[] nums) {
//        if(nums == null) return 0;
//        if(nums.length < 2) return nums.length;
//        Arrays.sort(nums);
//        int len = 1;
//        int res = 1;
//        for(int i = 1; i < nums.length; i++) {
//            if(nums[i] == nums[i-1]) {
//                continue;
//            }
//            if(nums[i] == nums[i-1] + 1) {
//                len++;
//            } else {
//                res = Math.max(len, res);
//                len = 1;
//            }
//        }
//        res = Math.max(len, res);
//        return res;
//    }
}
