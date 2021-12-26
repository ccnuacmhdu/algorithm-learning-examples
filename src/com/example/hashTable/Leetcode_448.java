package com.example.hashTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode_448 {

    // 思维
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = (nums[i] - 1) % n;
            nums[x] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }

    // 常规法
//    public List<Integer> findDisappearedNumbers(int[] nums) {
//        List<Integer> res = new ArrayList<>();
//        Set<Integer> set = new HashSet<>();
//        for (int i : nums) {
//            set.add(i);
//        }
//        for (int i = 1; i <= nums.length; i++) {
//            if (!set.contains(i)) {
//                res.add(i);
//            }
//        }
//        return res;
//    }

}
