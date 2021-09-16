package com.example.leetcode100hot;

import java.util.*;

public class Leetcode_104 {

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
    // 方法一
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    // 方法二
    public int maxDepth2(TreeNode root) {
        if(root == null) return 0;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        TreeNode t;
        int h = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for(int i = 0; i < size; i++) {
                t = que.poll();
                if(t.left != null) {
                    que.add(t.left);
                }
                if(t.right != null) {
                    que.add(t.right);
                }
            }
            h++;
        }
        return h;
    }
}
