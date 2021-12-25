package com.example.binaryTree;

import java.util.Arrays;
import java.util.LinkedList;

public class Leetcode_297 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return "#_";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(root.val).append("_");
        String left = serialize(root.left);
        String right = serialize(root.right);
        sb.append(left).append(right);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] ss = data.split("_");
        LinkedList<String> que = new LinkedList<>(Arrays.asList(ss));
        return deserialize(que);
    }
    private TreeNode deserialize(LinkedList<String> que) {
        String s = que.poll();
        if(s.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.left = deserialize(que);
        root.right = deserialize(que);
        return root;
    }
}
