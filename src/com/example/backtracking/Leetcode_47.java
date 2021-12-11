package com.example.backtracking;

import java.util.*;

public class Leetcode_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        process(nums, 0, new ArrayList<>(), res);
        return res;
    }
    private void process(int[] nums, int st, List<Integer> list, List<List<Integer>> res) {
        if(st == nums.length) {
            res.add(new ArrayList(list));
        }
        // 已经打过头的不能再打头（去重）
        Set<Integer> set = new HashSet<>();
        for(int i = st; i < nums.length; i++) {
            // ERROR！因swap导致乱序，如 0 9 0 1 0 就会导致两次 0 9 0 0 1，两次 0 9 0 0 1
            // if(i > st && nums[i] == nums[i-1]) continue;
            if(set.contains(nums[i])) continue;
            set.add(nums[i]);
            swap(nums, st, i);
            list.add(nums[st]);
            process(nums, st + 1, list, res);
            list.remove(list.size() - 1);
            swap(nums, st, i);
        }
    }
    private void swap(int[] a, int x, int y) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }
}
