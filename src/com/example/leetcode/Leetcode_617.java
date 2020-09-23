package com.example.leetcode;

public class Leetcode_617 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null) {
            return null;
        }
        if(t1 == null && t2 != null) {
            t1 = t2;
            mergeTrees(t1.left, null);
            mergeTrees(t2.right, null);
        }
        if(t1 != null && t2 == null) {
            mergeTrees(t1.left, null);
            mergeTrees(t1.right, null);
        }
        if(t1 != null && t2 != null) {
            t1.val += t2.val;
            mergeTrees(t1.left, t2.left);
            mergeTrees(t1.right, t2.right);
        }
        return t1;
    }
}
