package com.example.binaryTree;

import java.util.*;

public class Leetcode_235 {

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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> pMap = new HashMap<>();
        setParent(root, pMap);
        if(root != null) pMap.put(root, null);
        Set<TreeNode> pParents = new HashSet<>();
        while (p != null) {
            pParents.add(p);
            p = pMap.get(p);
        }
        while (q != null) {
            if(pParents.contains(q)) {
                return q;
            }
            q = pMap.get(q);
        }
        return null;
    }
    private void setParent(TreeNode root, Map<TreeNode, TreeNode> pMap) {
        if(root == null) return;
        if(root.left != null) {
            pMap.put(root.left, root);
        }
        if(root.right != null) {
            pMap.put(root.right, root);
        }
        setParent(root.left, pMap);
        setParent(root.right, pMap);
    }

}
