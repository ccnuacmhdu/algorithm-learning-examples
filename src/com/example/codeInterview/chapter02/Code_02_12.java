package com.example.codeInterview.chapter02;

import java.util.Stack;

// [Leetcode]25. K 个一组翻转链表
public class Code_02_12 {
    private static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * 【方法一，利用栈】
     *
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKNodes(Node head, int k) {
        if(k < 2) {
            return head;
        }
        Node newHead = head;
        Node cur = head;
        Node left = null;
        Node next = null;
        Stack<Node> stack = new Stack<>();
        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if(stack.size() == k) {
                left = reassign(stack, left, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    // 左闭右开
    private static Node reassign(Stack<Node> stack, Node left, Node right) {
        Node cur = stack.pop();
        if(left != null) {
            left.next = cur;
        }
        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }

    /**
     * 【方法二：不用栈】
     *
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKNodes2(Node head, int k) {
        if(k < 2) {
            return head;
        }
        int cnt = 0;
        Node cur = head;
        Node left = null;
        Node next = null;
        Node start = head;
        Node newHead = head;
        while (cur != null) {
            next = cur.next;
            cnt++;
            if(cnt == k) {
                reassign2(left, next, start, cur);
                newHead = left == null ? cur : newHead;
                left = start;
                start = next;
                cnt = 0;
            }
            cur = next;
        }
        return newHead;
    }

    // 左闭右开
    private static void reassign2(Node left, Node right, Node start, Node end) {
        Node cur = start;
        Node next = null;
        Node pre = right;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        end.next = pre;
        if(left != null) {
            left.next = end;
        }
    }
}
