package com.example.leetcodeTmp;

import java.util.ArrayList;
import java.util.List;

/**
 * 【组合】
 */
public class Leetcode_77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n <= 0 || k > n || k <= 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        f(n, k, 1, res, list);
        return res;
    }
    private void f(int n, int k, int st, List<List<Integer>> res, List<Integer> list) {
        if(list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        // （剪枝）每轮开始时已经有了 list.size() 个，最多 k 个即可，那么对于当前 st 来说，最多 k - list.size() 个，
        // 那么 i 最大只能取 n - (k - list.size()) + 1
        for(int i = st; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            f(n, k, i+1, res, list);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        Leetcode_77 leetcode_77 = new Leetcode_77();
        List<List<Integer>> res = leetcode_77.combine(4, 2);
        System.out.println(res);
    }
}
