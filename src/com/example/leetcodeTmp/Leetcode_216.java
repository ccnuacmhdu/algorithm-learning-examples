package com.example.leetcodeTmp;

import java.util.*;

/**
 * 【组合总和 III】
 */
public class Leetcode_216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if(k <= 0 || n <= 0 || n > 45 || k > 9) {
            return res;
        }
        f(1, k, n, res, new ArrayList<>());
        return res;
    }
    private void f(int st, int k, int n, List<List<Integer>> res, List<Integer> list) {
        if(k == 0 && n == 0) {
            res.add(new ArrayList<>(list));
        }
        if(k > 0 && n > 0) {
            for (int i = st; i <= 9; i++) {
                if(i > n) {
                    break;
                }
                list.add(i);
                f(i + 1, k - 1, n - i, res, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
