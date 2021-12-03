package com.example.binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JZ_37 {

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
            return "#,";
        }
        String res = root.val + ",";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] ss = data.split(",");
        List<String> que = new LinkedList<>(Arrays.asList(ss));
        return deserialize(que);
    }
    private TreeNode deserialize(List<String> que) {
        if( "#".equals(que.get(0))) {
            que.remove(0);
            return null;
        }
        int v = Integer.valueOf(que.remove(0));
        TreeNode root = new TreeNode(v);
        root.left = deserialize(que);
        root.right = deserialize(que);
        return root;
    }
}
