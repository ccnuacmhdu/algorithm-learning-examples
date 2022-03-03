package com.example.binaryTree;

public class Leetcode_124 {
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

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return max;
    }

    /**
     * 以root为根节点的最大贡献值 = max(左孩子的最大贡献值, 右孩子的最大贡献值) + root.val
     *
     * @param root
     * @return
     */
    private int maxGain(TreeNode root) {
        if(root == null) return 0;
        int lGain = Math.max(maxGain(root.left), 0);
        int rGain = Math.max(maxGain(root.right), 0);

        // 以root为根节点的最大路径和，遍历所有节点，求最大值
        int pathSum = lGain + rGain + root.val;
        max = Math.max(max, pathSum);

        return Math.max(lGain, rGain) + root.val;
    }

}
