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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<TreeNode, Integer> high = new HashMap<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        TreeNode t = null;
        que.add(root);
        high.put(root, 0);
        while (!que.isEmpty()) {
            t = que.poll();
            int h = high.get(t);
            if(h >= res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(h).add(t.val);
            if(t.left != null) {
                que.add(t.left);
                high.put(t.left, h + 1);
            }
            if(t.right != null) {
                que.add(t.right);
                high.put(t.right, h + 1);
            }
        }
        return res;
    }
}