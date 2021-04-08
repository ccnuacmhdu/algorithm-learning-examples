package com.example.codeinterview.chapter03;

// [leetcode] 110. 平衡二叉树
public class Code_03_10 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    private class ReturnType {
        public int h;
        public boolean isBalanced;

        public ReturnType(int h, boolean isBalanced) {
            this.h = h;
            this.isBalanced = isBalanced;
        }
    }
    private ReturnType process(TreeNode root) {
        if(root == null) {
            return new ReturnType(0, true);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        int h = Math.max(leftData.h, rightData.h) + 1;
        boolean isBalanced = leftData.isBalanced
                && rightData.isBalanced
                && Math.abs(leftData.h - rightData.h) <= 1;
        return new ReturnType(h, isBalanced);
    }
}
