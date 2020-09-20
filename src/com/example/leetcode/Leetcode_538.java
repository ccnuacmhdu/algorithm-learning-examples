package com.example.leetcode;

import java.util.Stack;

/**
 * 【把二叉搜索树转换为累加树】
 */
public class Leetcode_538 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 【方式一：中序遍历】
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        f(root, stack);
        TreeNode next = stack.pop();
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            cur.val += next.val;
            next = cur;
        }
        return root;
    }
    private void f(TreeNode root, Stack<TreeNode> stack) {
        if(root != null) {
            f(root.left, stack);
            stack.push(root);
            f(root.right, stack);
        }
    }

    /**
     * 【方式二：官方题解思路，打破常规先序/中序/后序思维，右-根-左】
     * 先根/后根/中根分别对应了两种遍历顺序，共六种顺序，不要被传统三种遍历方式束缚。。。
     */
    private int sum = 0;
    public TreeNode convertBST_02(TreeNode root) {
        if(root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
