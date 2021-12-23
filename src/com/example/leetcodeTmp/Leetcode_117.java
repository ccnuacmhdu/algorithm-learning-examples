package com.example.leetcodeTmp;

import java.util.LinkedList;

/**
 * 【填充每个节点的下一个右侧节点指针 II】
 */
public class Leetcode_117 {

    public Node connect(Node root) {
        if(root == null) {
            return root;
        }
        LinkedList<Node> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int size = que.size();
            Node pre = null;
            Node tmp = null;
            for(int i = 0; i < size; i++) {
                tmp = que.poll();
                if(tmp.left != null) {
                    que.add(tmp.left);
                }
                if(tmp.right != null) {
                    que.add(tmp.right);
                }
                if(i == 0) {
                    pre = tmp;
                    continue;
                }
                pre.next = tmp;
                pre = tmp;
            }
            tmp.next = null;
        }
        return root;
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
