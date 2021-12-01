package com.example.binaryTree;

import java.util.LinkedList;

/**
 * 有左有右（√）
 * 有左无右（√）
 * 有右无左（×）
 * 无左无右（√），一旦出现叶子，后边只能是叶子
 */
public class Leetcode_958 {

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

    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return true;
        LinkedList<TreeNode> que = new LinkedList<>();
        TreeNode t = null;
        boolean leafAppeared = false;
        que.add(root);
        while (!que.isEmpty()) {
            t = que.poll();
            if(t.left == null && t.right != null) {
                return false;
            }
            if(leafAppeared && (t.left != null || t.right != null)) {
                return false;
            }
            if(t.left == null || t.right == null) {
                leafAppeared = true;
            }
            if(t.left != null) {
                que.add(t.left);
            }
            if(t.right != null) {
                que.add(t.right);
            }
        }
        return true;
    }
}
