package com.example.binaryTree;

public class Leetcode_98 {

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

    public boolean isValidBST(TreeNode root) {
        return process(root).isBST();
    }

    private ReturnType process(TreeNode root) {
        if(root == null) {
            return new ReturnType(Long.MAX_VALUE, Long.MIN_VALUE, true);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        long min = Math.min(root.val, Math.min(leftData.min, rightData.min));
        long max = Math.max(root.val, Math.max(leftData.max, rightData.max));
        boolean isBST = leftData.isBST() && rightData.isBST()
                && leftData.getMax() < root.val && root.val < rightData.getMin();
        return new ReturnType(min, max, isBST);
    }

    private class ReturnType {
        private long min;
        private long max;
        private boolean isBST;

        public ReturnType(long min, long max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }

        public long getMin() {
            return min;
        }

        public long getMax() {
            return max;
        }

        public boolean isBST() {
            return isBST;
        }
    }

}
