package com.example.leetcode;

/**
 * 【左叶子之和】
 */
public class Leetcode_404 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int sumOfLeftLeaves(TreeNode root) {
        int res = 0;
        if(root != null) {
            if(root.left != null && root.left.left == null && root.left.right == null) {
                res += root.left.val;
            }
            res += sumOfLeftLeaves(root.left);
            res += sumOfLeftLeaves(root.right);
        }
        return res;
    }
}
