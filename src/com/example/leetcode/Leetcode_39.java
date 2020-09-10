package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        f(0, candidates, res,new ArrayList<>(), target);
        return res;
    }
    private void f(int st, int[] candidates, List<List<Integer>> res, List<Integer> list, int target) {
        if(target < 0) {
            return;
        }
        if(0 == target) {
            res.add(new ArrayList<>(list));
        }
        for(int i = st; i < candidates.length; i++) {
            if(candidates[i] > target) {
                break;
            }
            list.add(candidates[i]);
            f(i, candidates, res, list, target-candidates[i]);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        Leetcode_39 leetcode_39 = new Leetcode_39();
        int[] a = {1, 2};
        int target = 4;
        List<List<Integer>> res = leetcode_39.combinationSum(a, target);
        System.out.println(res);
    }
}
