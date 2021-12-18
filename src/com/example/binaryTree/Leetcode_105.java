package com.example.binaryTree;

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
        return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    private TreeNode process(int[] preorder, int preSt, int preEn, int[] inorder, int inSt, int inEn) {
        if(preSt > preEn) return null;
        TreeNode r = new TreeNode(preorder[preSt]);
        int loc = getLoc(inorder, preorder[preSt], inSt, inEn);
        r.left = process(preorder, preSt + 1, preSt + loc - inSt, inorder, inSt, loc - 1);
        r.right = process(preorder, preSt + loc - inSt + 1, preEn, inorder, loc + 1, inEn);
        return r;
    }
    private int getLoc(int[] a, int v, int st, int en) {
        for(int i = st; i <= en; i++) {
            if(a[i] == v) {
                return i;
            }
        }
        return -1;
    }
}
