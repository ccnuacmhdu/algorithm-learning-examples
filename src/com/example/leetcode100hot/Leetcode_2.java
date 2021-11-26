package com.example.leetcode100hot;

public class Leetcode_2 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode t1 = l1, t2 = l2;
        ListNode r = dummy;
        int carry = 0;
        while(t1 != null && t2 != null) {
            r.next = new ListNode((t1.val + t2.val + carry) % 10);
            r = r.next;
            carry = (t1.val + t2.val + carry) / 10;
            t1 = t1.next;
            t2 = t2.next;
        }
        while(t1 != null) {
            r.next = new ListNode((t1.val + carry) % 10);
            r = r.next;
            carry = (t1.val + carry) / 10;
            t1 = t1.next;
        }
        while(t2 != null) {
            r.next = new ListNode((t2.val + carry) % 10);
            r = r.next;
            carry = (t2.val + carry) / 10;
            t2 = t2.next;
        }
        if(carry == 1) {
            r.next = new ListNode(1);
        }
        return dummy.next;
    }
}
