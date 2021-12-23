package com.example.leetcodeTmp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 【全排列 II】
 */
public class Leetcode_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        f(nums, 0, res);
        return res;
    }

    /**
     * 每次确定 st 位置上的数，每次可以 nums[st~nums.length-1] 打头
     *
     * @param nums
     * @param st
     * @param res
     */
    private void f(int[] nums, int st, List<List<Integer>> res) {
        if(st == nums.length) {
            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            res.add(list);
        }
        Set<Integer> set = new HashSet<>();
        // 每次以 nums[i] 打头，递归再以 st+1 位置的数打头
        for(int i = st; i < nums.length; i++) {
            if(!set.contains(nums[i])) {
                set.add(nums[i]);
                swap(nums, st, i);
                // 注意 st+1 正好是确定下一个位置的数字
                f(nums, st + 1, res);
                swap(nums, st, i);
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Leetcode_47 leetcode_47 = new Leetcode_47();
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = leetcode_47.permuteUnique(nums);
        System.out.println(res);
    }
}
