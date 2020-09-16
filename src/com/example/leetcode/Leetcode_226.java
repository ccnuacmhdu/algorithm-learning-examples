package com.example.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 【反转二叉树】
 */
public class Leetcode_226 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 【方法一：DFS】
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root != null) {
            TreeNode tmpRight = root.right;
            root.right = root.left;
            root.left = tmpRight;
            if(root.left != null) {
                invertTree(root.left);
            }
            if(root.right != null) {
                invertTree(root.right);
            }
        }
        return root;
    }

    /**
     * 【方法二：BFS】
     *
     * @param root
     * @return
     */
    public TreeNode bfs(TreeNode root) {
        if(root == null) {
            return root;
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            root = que.poll();
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            if(root.left != null) {
                que.add(root.left);
            }
            if(root.right != null) {
                que.add(root.right);
            }
        }
        return root;
    }

    /**
     * 【方式三：DFS 非递归】
     *
     * @param root
     * @return
     */
    public TreeNode dfs(TreeNode root) {
        if(root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode rootTmp = root;
        while (!stack.isEmpty()) {
            root = stack.pop();
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = tmp;
            if(root.right != null) {
                stack.push(root.right);
            }
            if(root.left != null) {
                stack.push(root.left);
            }
        }
        return rootTmp;
    }
}
