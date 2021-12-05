package com.example.simulation;

public class Leetcode_2 {

   private class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    // 模拟
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode p1 = l1, p2 = l2;
//        ListNode dummy = new ListNode(0);
//        ListNode t = dummy;
//        int carry = 0;
//        int q = 0;
//        while(p1 != null && p2 != null) {
//            q = (p1.val + p2.val + carry) % 10;
//            carry = (p1.val + p2.val + carry) / 10;
//            t.next = new ListNode(q);
//            t = t.next;
//            p1 = p1.next;
//            p2 = p2.next;
//        }
//        while(p1 != null) {
//            q = (p1.val + carry) % 10;
//            carry = (p1.val + carry) / 10;
//            t.next = new ListNode(q);
//            t = t.next;
//            p1 = p1.next;
//        }
//        while(p2 != null) {
//            q = (p2.val + carry) % 10;
//            carry = (p2.val + carry) / 10;
//            t.next = new ListNode(q);
//            t = t.next;
//            p2 = p2.next;
//        }
//        if(carry == 1) {
//            t.next = new ListNode(1);
//        }
//        return dummy.next;
//    }

    // 模拟（优化重复代码）
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode t = dummy;
        int q = 0, carry = 0, val1 = 0, val2 = 0;
        while (p1 != null || p2 != null) {
            val1 = p1 != null ? p1.val : 0;
            val2 = p2 != null ? p2.val : 0;
            q = (val1 + val2 + carry) % 10;
            carry = (val1 + val2 + carry) / 10;
            t.next = new ListNode(q);
            t = t.next;
            p1 = p1 == null ? null : p1.next;
            p2 = p2 == null ? null : p2.next;
        }
        if(carry == 1) {
            t.next = new ListNode(1);
        }
        return dummy.next;
    }
}
