package com.example.leetcode100hot;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_114 {

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

    public void flatten(TreeNode root) {
        if(root == null) return;
        List<TreeNode> list = new ArrayList<>();
        process(root, list);
        for(int i = 1; i < list.size(); i++) {
            list.get(i - 1).right = list.get(i);
            list.get(i - 1).left = null;
        }
    }
    private void process(TreeNode root, List<TreeNode> list) {
        if(root != null) {
            list.add(root);
            process(root.left, list);
            process(root.right, list);
        }
    }
}
