package com.example.leetcodeTmp;

import java.util.*;

/**
 * 【二叉树的层次遍历 II】
 * 普通的 BFS（层序遍历二叉树）
 */
public class Leetcode_107 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);

        Map<TreeNode, Integer> map = new LinkedHashMap<>();
        map.put(root, 0);
        while (!que.isEmpty()) {
            root = que.poll();
            if(root.left != null) {
                que.add(root.left);
                map.put(root.left, map.get(root)+1);
            }
            if(root.right != null) {
                que.add(root.right);
                map.put(root.right, map.get(root)+1);
            }
        }

        int h = Collections.max(map.values());
        res = new ArrayList<List<Integer>>();
        for(int i = 0; i <= h; i++) {
            res.add(new ArrayList<>());
        }
        for(Map.Entry entry: map.entrySet()) {
            TreeNode node = (TreeNode) entry.getKey();
            int value = (int) entry.getValue();
            res.get(h-value).add(node.val);
        }
        return res;
    }
}


