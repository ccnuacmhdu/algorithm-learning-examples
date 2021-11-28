package com.example.codeInterview.chapter02;

// [Leetcode] 237. 删除链表中的节点
public class Code_02_17 {
    private class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
