package com.example.leetcodeTmp;

import java.util.ArrayList;
import java.util.List;

/**
 * 【二叉树的所有路径】
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Leetcode_257 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    /**
     * 【解法一：递归+回溯】
     * @param root
     * @return
     */
    public List<String> binaryTreePaths_01(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList();
        process_01(root, sb, res);
        return res;
    }
    private void process_01(TreeNode root, StringBuilder sb, List<String> res) {
        if(root != null) {
            sb.append(root.val);
            if(root.left == null && root.right == null) {
                res.add(sb.toString());
            }
            if(root.left != null) {
                sb.append("->");
                process_01(root.left, sb, res);
                sb.delete(sb.lastIndexOf("->"), sb.length());
            }
            if(root.right != null) {
                sb.append("->");
                process_01(root.right, sb, res);
                sb.delete(sb.lastIndexOf("->"), sb.length());
            }
        }
    }

    /**
     * 【解法二：DFS】
     * @param root
     * @return
     */
    public List<String> binaryTreePaths_02(TreeNode root) {
        List<String> res = new ArrayList();
        process_02(root, "", res);
        return res;
    }
    private void process_02(TreeNode root, String s, List<String> res) {
        if(root != null) {
            StringBuilder sb = new StringBuilder(s);
            sb.append(root.val);
            if(root.left == null && root.right == null) {
                res.add(sb.toString());
            } else {
                sb.append("->");
                process_02(root.left, sb.toString(), res);
                process_02(root.right, sb.toString(), res);
            }
        }
    }
}
