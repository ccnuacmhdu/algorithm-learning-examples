package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        f(0, candidates, res,new ArrayList<>(), target);
        return res;
    }
    private void f(int st, int[] candidates, List<List<Integer>> res, List<Integer> list, int target) {
        if(target < 0) {
            return;
        }
        // 使用 for 循环中的小剪枝可不用下面该行的暴力去重
        if(0 == target /*&& !isDuplicated(res, list)*/) {
            res.add(new ArrayList<>(list));
        }
        for(int i = st; i < candidates.length; i++) {
            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过
            if (i > st && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if(candidates[i] > target) {
                break;
            }
            list.add(candidates[i]);
            f(i+1, candidates, res, list, target-candidates[i]);
            list.remove(list.size()-1);
        }
    }
    private boolean isDuplicated(List<List<Integer>> res, List<Integer> list) {
        for(List<Integer> l: res) {
            if(l.size() == list.size()) {
                boolean flag = true;
                for(int i = 0; i < list.size(); i++) {
                    if(l.get(i) != list.get(i)) {
                        flag = false;
                    }
                }
                if(flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Leetcode_40 leetcode_39 = new Leetcode_40();
        int[] a = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> res = leetcode_39.combinationSum2(a, target);
        System.out.println(res);
    }
}
