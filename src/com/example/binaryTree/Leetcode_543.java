package com.example.binaryTree;

public class Leetcode_543 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    private int max;

    public int diameterOfBinaryTree(TreeNode root) {
        f(root);
        return max;
    }
    // 枚举以每个节点为根取得的最大直径
    private void f(TreeNode root) {
        if(root == null) {
            return;
        }
        max = Math.max(max, d(root.left) + d(root.right));
        f(root.left);
        f(root.right);
    }
    private int d(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(d(root.left), d(root.right)) + 1;
    }


    /**
     * 模仿 Leetcode_124 的思路
     */
//    class Solution {
//        private int max;
//
//        public int diameterOfBinaryTree(TreeNode root) {
//            maxGain(root);
//            return max;
//        }
//        private int maxGain(TreeNode root) {
//            if(root == null) return 0;
//            int lGain = Math.max(maxGain(root.left), 0);
//            int rGain = Math.max(maxGain(root.right), 0);
//            max = Math.max(max, lGain + rGain);
//            return Math.max(lGain, rGain) + 1;
//        }
//    }

}
