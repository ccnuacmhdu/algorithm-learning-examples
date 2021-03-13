package com.example.codeinterview.chapter02;

// [Leetcode] 21. 合并两个有序链表
public class Code_02_19 {

    private class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode root = l1.val <= l2.val ? l1 : l2;
        ListNode cur = root;
        ListNode tmp1 = l1.val <= l2.val ? l1.next : l1;
        ListNode tmp2 = l1.val <= l2.val ? l2 : l2.next;
        while(tmp1 != null && tmp2 != null) {
            if(tmp1.val <= tmp2.val) {
                cur.next = tmp1;
                tmp1 = tmp1.next;
            } else {
                cur.next = tmp2;
                tmp2 = tmp2.next;
            }
            cur = cur.next;
        }
        cur.next = tmp1 == null ? tmp2 : tmp1;
        return root;
    }
}
