package com.example.binaryTree;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_105 {

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

    // 1 2 3 4
    // 3 2 1 4
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        buildIndexMap(inorder, indexMap);
        return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, indexMap);
    }
    private TreeNode process(int[] preorder, int preSt, int preEn, int[] inorder, int inSt, int inEn, Map<Integer, Integer> indexMap) {
        if(preSt > preEn) return null;
        TreeNode r = new TreeNode(preorder[preSt]);
        int loc = indexMap.get(preorder[preSt]);    // 提前存Map，快速查找
        r.left = process(preorder, preSt + 1, preSt + loc - inSt, inorder, inSt, loc - 1, indexMap);
        r.right = process(preorder, preSt + loc - inSt + 1, preEn, inorder, loc + 1, inEn, indexMap);
        return r;
    }
    private void buildIndexMap(int[] a, Map<Integer, Integer> map) {
        for(int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }
    }
}
