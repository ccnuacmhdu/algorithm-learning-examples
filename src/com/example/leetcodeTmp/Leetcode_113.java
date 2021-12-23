package com.example.leetcodeTmp;

import java.util.*;

/**
 * 【路径总和 II】
 */
public class Leetcode_113 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        f(root, new ArrayList<>(), res, sum);
        return res;
    }
    private void f(TreeNode root, List<Integer> list, List<List<Integer>> res, int sum) {
        if(root != null) {
            list.add(root.val);
            sum -= root.val;
            if(root.left == null && root.right == null) {
                if(sum == 0) {
                    res.add(new ArrayList<Integer>(list));
                }
            }
            if(root.left != null) {
                f(root.left, list, res, sum);
                list.remove(list.size()-1);
            }
            if(root.right != null) {
                f(root.right, list, res, sum);
                list.remove(list.size()-1);
            }
        }
    }
}
