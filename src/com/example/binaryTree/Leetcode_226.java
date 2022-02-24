package com.example.binaryTree;

import java.util.LinkedList;

public class Leetcode_226 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return root;
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()) {
            TreeNode poll = que.poll();
            TreeNode t = poll.left;
            poll.left = poll.right;
            poll.right = t;
            if(poll.left != null) {
                que.add(poll.left);
            }
            if(poll.right != null) {
                que.add(poll.right);
            }
        }
        return root;
    }
}
