package com.example.leetcode100hot;

import java.util.*;

public class Leetcode_102 {

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
    // 方法一
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

    // 方法二
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        LinkedList<TreeNode> que = new LinkedList<>();
        TreeNode t;
        que.add(root);
        while (!que.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = que.size();
            for(int i = 0; i < size; i++) {
                t = que.poll();
                list.add(t.val);
                if(t.left != null) {
                    que.add(t.left);
                }
                if(t.right != null) {
                    que.add(t.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
