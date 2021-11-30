package com.example.linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表相交的一系列问题都可以用Set解决 😊
 */
public class Leetcode_160 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode t = headA;
        while(t != null) {
            set.add(t);
            t = t.next;
        }
        t = headB;
        while(t != null) {
            if(set.contains(t)) {
                return t;
            }
            t = t.next;
        }
        return null;
    }
}
