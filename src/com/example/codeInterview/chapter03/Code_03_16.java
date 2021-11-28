package com.example.codeInterview.chapter03;

// [leetcode] 543. 二叉树的直径
public class Code_03_16 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int diameterOfBinaryTree(TreeNode root) {
        return process(root).d;
    }
    private class ReturnType {
        public int h;   // 高度
        public int d;   // 距离

        public ReturnType(int h, int d) {
            this.h = h;
            this.d = d;
        }
    }
    private ReturnType process(TreeNode root) {
        if(root == null) {
            return new ReturnType(0, 0);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        int h = Math.max(leftData.h, rightData.h) + 1;
        int d = Math.max(leftData.h + rightData.h, Math.max(leftData.d, rightData.d));
        return new ReturnType(h, d);
    }
}
