package com.example.leetcodeTmp;


import java.util.*;

/**
 * 【二叉树的层平均值】
 */
public class Leetcode_637 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Double> averageOfLevels_01(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Map<TreeNode, Integer> level = new HashMap<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        level.put(root, 1);
        int curH = 1;
        int num = 0;
        long sum = 0;
        while (!que.isEmpty()) {
            root = que.poll();
            if(curH != level.get(root)) {
                res.add((double)sum/num);
                sum = root.val;
                num = 1;
                curH++;
            } else {
                sum += root.val;
                num++;
            }
            if(root.left != null) {
                que.add(root.left);
                level.put(root.left, level.get(root) + 1);
            }
            if(root.right != null) {
                que.add(root.right);
                level.put(root.right, level.get(root) + 1);
            }
        }
        res.add((double)sum/num);
        return res;
    }

    /**
     * 【优化】
     * 入队列一层，弹出一层。。。
     * @param root
     * @return
     */
    public List<Double> averageOfLevels_02(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            long sum = 0;
            long num = que.size();
            for(int i = 0; i < num; i++) {
                root = que.poll();
                sum += root.val;
                if(root.left != null) {
                    que.add(root.left);
                }
                if(root.right != null) {
                    que.add(root.right);
                }
            }
            res.add((double)sum/num);
        }
        return res;
    }
}
