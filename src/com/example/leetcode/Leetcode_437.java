package com.example.leetcode;

public class Leetcode_437 {
    class TreeNode {
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
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        int sum = dfs(root, targetSum);
        sum += pathSum(root.left, targetSum);
        sum += pathSum(root.right, targetSum);
        return sum;
    }
    private int dfs(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        int ret = 0;
        if(root.val == targetSum) {
            ret++;
        }
        ret += dfs(root.left, targetSum - root.val);
        ret += dfs(root.right, targetSum - root.val);
        return ret;
    }

}
