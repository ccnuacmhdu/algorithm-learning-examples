package com.example;

import java.awt.datatransfer.FlavorListener;
import java.sql.Struct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        String[] strs = new String[]{"bdddddddddd","bbbbbbbbbbc"};
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