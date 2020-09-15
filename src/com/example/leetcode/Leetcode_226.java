package com.example.leetcode;

/**
 * 【反转二叉树】
 */
public class Leetcode_226 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode invertTree(TreeNode root) {
        if(root != null) {
            TreeNode tmpRight = root.right;
            root.right = root.left;
            root.left = tmpRight;
            if(root.left != null) {
                invertTree(root.left);
            }
            if(root.right != null) {
                invertTree(root.right);
            }
        }
        return root;
    }
}
