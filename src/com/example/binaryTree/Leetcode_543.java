package com.example.binaryTree;

public class Leetcode_543 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int lh = h(root.left);
        int rh = h(root.right);
        int ld = diameterOfBinaryTree(root.left);
        int rd = diameterOfBinaryTree(root.right);
        return Math.max(lh + rh, Math.max(ld, rd));
    }
    private int h(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(h(root.left), h(root.right)) + 1;
    }
}
