package com.example.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        process(nums, res, new ArrayList<Integer>(), 0);
        return res;
    }
    private void process(int[] nums, List<List<Integer>> res, List<Integer> list, int st) {
        res.add(new ArrayList<Integer>(list));
        // 递归地分别以 i 打头
        for(int i = st; i < nums.length; i++) {
            list.add(nums[i]);
            process(nums, res, list, i + 1);
            list.remove(list.size() - 1);
        }

        // 等价以上逻辑
//        if(st == nums.length) {
//            res.add(new ArrayList<Integer>(list));
//            return;
//        }
//        list.add(nums[st]);
//        process(nums, res, list, st + 1);
//        list.remove(list.size() - 1);
//        process(nums, res, list, st + 1);
    }
}
