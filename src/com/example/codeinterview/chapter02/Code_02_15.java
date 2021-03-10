package com.example.codeinterview.chapter02;

import java.util.*;

// [剑指Offer] 36. 二叉搜索树转双向循环链表
public class Code_02_15 {

    private class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 【方法一：中序遍历+拼接】
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        inOrder(root, queue);
        Node head = queue.poll();
        Node last = head;
        last.left = null;
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            last.right = cur;
            cur.left = last;
            last = cur;
        }
        head.left = last;
        last.right = head;  // 防止遍历时死循环
        return head;
    }

    private void inOrder(Node tree, Queue<Node> queue) {
        if(tree != null) {
            inOrder(tree.left, queue);
            queue.add(tree);
            inOrder(tree.right, queue);
        }
    }

    /**
     * 【方法二：二叉树套路】
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList2(Node root) {
        if (root == null) {
            return null;
        }
        ReturnType returnType = process(root);
        returnType.start.left = returnType.end;
        returnType.end.right = returnType.start;
        return returnType.start;
    }

    private ReturnType process(Node root) {
        if(root == null) {
            return new ReturnType(null, null);
        }
        ReturnType leftReturnType = process(root.left);
        ReturnType rightReturnType = process(root.right);
        if(leftReturnType.end != null) {
            leftReturnType.end.right = root;
        }
        if(rightReturnType.start != null) {
            rightReturnType.start.left = root;
        }
        root.left = leftReturnType.end;
        root.right = rightReturnType.start;
        return new ReturnType(leftReturnType.start != null ? leftReturnType.start : root,
                              rightReturnType.end != null ? rightReturnType.end : root);
    }

    private class ReturnType {
        public Node start;
        public Node end;

        public ReturnType(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }
}
