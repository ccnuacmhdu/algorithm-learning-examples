package com.example.codeInterview.chapter03;

// 剑指 Offer 26. 树的子结构
public class Code_03_09 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(B == null) {
            return false;
        }
        return contains(A, B);
    }
    private boolean contains(TreeNode A, TreeNode B) {
        if(B == null) {
            return true;
        }
        if(A == null) {
            return false;
        }
        return check(A, B) || contains(A.left, B) || contains(A.right, B);
    }
    private boolean check(TreeNode A, TreeNode B) {
        if(B == null) {
            return true;
        }
        if(A == null || A.val != B.val) {
            return false;
        }
        return check(A.left, B.left) && check(A.right, B.right);
    }
}
