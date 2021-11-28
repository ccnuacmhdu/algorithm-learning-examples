package com.example.linkedList;

public class Leetcode_92 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode t = head, next = null, pre = null;
        ListNode finalPre = null, finalRight = null;
        int loc = 1;
        while (t != null) {
            if(loc == left) {
                finalPre = pre;
                finalRight = t;
                while (loc != right) {
                    next = t.next;
                    t.next = pre;
                    pre = t;
                    t = next;
                    loc++;
                }
                next = t.next;
                t.next = pre;
                finalRight.next = next;
                if(finalPre == null) return t;  //
                finalPre.next = t;
                break;  //
            } else {
                next = t.next;
                pre = t;
                t = next;
                loc++;
            }
        }
        return head;
    }
}
