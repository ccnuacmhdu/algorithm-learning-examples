package com.example.binaryTree;

public class Leetcode_110 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

//    public boolean isBalanced(TreeNode root) {
//        if(root == null) {
//            return true;
//        }
//
//        int LH = getH(root.left);
//        int RH = getH(root.right);
//
//        return Math.abs(LH - RH) <= 1 && isBalanced(root.left) && isBalanced(root.right);
//
//    }
//    private int getH(TreeNode root) {
//        if(root == null) {
//            return 0;
//        }
//        return Math.max(getH(root.left), getH(root.right)) + 1;
//    }

    // 套路
    public boolean isBalanced(TreeNode root) {
        return process(root).isBalanced();
    }

    private ReturnType process(TreeNode root) {
        if(root == null) {
            return new ReturnType(0, true);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        int h = Math.max(leftData.getH(), rightData.getH()) + 1;
        boolean isBalanced = leftData.isBalanced() && rightData.isBalanced()
                && Math.abs(leftData.getH() - rightData.getH()) <= 1;
        return new ReturnType(h, isBalanced);
    }

    private class ReturnType {
        private int h;
        private boolean isBalanced;

        public ReturnType(int h, boolean isBalanced) {
            this.h = h;
            this.isBalanced = isBalanced;
        }

        public int getH() {
            return h;
        }

        public boolean isBalanced() {
            return isBalanced;
        }
    }

}
