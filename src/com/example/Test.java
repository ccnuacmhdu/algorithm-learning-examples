package com.example;

import java.awt.datatransfer.FlavorListener;
import java.sql.Struct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        Solution solution = new Solution();
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

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

class Solution {
    /**
     * 一棵树是否是对称的，指的是左树和右树是否对称，取决于，
     * 1）左树根和右树根相等
     * 2）左树的左孩子和右树的右孩子是否对称
     * 3）左树的右孩子和右树的左孩子是否对称
     *
     * 重要思想！！！树 T 是否对称，不就是判定 T 和 T 是否对称的吗？！可以假定一个 dummy 树根，
     * 其左右孩子都是 root，转化成判定 dummy 是否对称，转化成左树 root 和 右树 root 是否对称
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return process(root, root);
    }
    private boolean process(TreeNode r1, TreeNode r2) {
        if(r1 == null && r2 == null) {
            return true;
        }
        if(r1 == null || r2 == null) {
            return false;
        }
        return r1.val == r2.val && process(r1.left, r2.right) && process(r1.right, r2.left);
    }
}