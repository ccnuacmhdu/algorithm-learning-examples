package com.example.codeinterview.chapter03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 剑指 Offer 68 - II. 二叉树的最近公共祖先
public class Code_03_15 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        preOrder(root, map);
        map.put(root, null);
        Set<TreeNode> set = new HashSet<>();
        while(p != null && map.containsKey(p)) {
            set.add(p);
            p = map.get(p);
        }
        while(q != null && map.containsKey(q)) {
            if(set.contains(q)) {
                return q;
            }
            q = map.get(q);
        }
        return null;
    }
    private void preOrder(TreeNode root, Map<TreeNode, TreeNode> map) {
        if(root == null) {
            return;
        }
        if(root.left != null) {
            map.put(root.left, root);
        }
        if(root.right != null) {
            map.put(root.right, root);
        }
        preOrder(root.left, map);
        preOrder(root.right, map);
    }
}
