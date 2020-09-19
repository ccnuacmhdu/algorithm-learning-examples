package com.example.leetcode;

import java.util.*;

/**
 * 【子集】
 */
public class Leetcode_78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        f(nums, 0, new ArrayList<>(), res);
        return res;
    }
    private void f(int[] nums, int idx, List<Integer> list, List<List<Integer>> res) {
        if(idx <= nums.length) {
            res.add(new ArrayList<>(list));
        }
        for(int i = idx; i < nums.length; i++) {
            list.add(nums[i]);
            f(nums, i + 1, list, res);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        Leetcode_78 leetcode_78 = new Leetcode_78();
        List<List<Integer>> subsets = leetcode_78.subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }
}
