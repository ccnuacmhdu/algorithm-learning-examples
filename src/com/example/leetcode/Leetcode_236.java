package com.example.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode_236 {
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        dfs(root, map);
        Set<TreeNode> set = new HashSet();
        TreeNode t = p;
        while(t != root) {
            set.add(t);
            t = map.get(t);
        }
        t = q;
        while(t != root) {
            if(set.contains(t)) {
                return t;
            }
            t = map.get(t);
        }
        return root;
    }
    private void dfs(TreeNode root, Map<TreeNode, TreeNode> map) {
        if(root != null) {
            if(root.left != null) map.put(root.left, root);
            if(root.right != null) map.put(root.right, root);
            dfs(root.left, map);
            dfs(root.right, map);
        }
    }
}
