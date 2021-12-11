package com.example.linkedList;

import java.util.PriorityQueue;

public class Leetcode_23 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 两两合并
//    public ListNode mergeKLists(ListNode[] lists) {
//        if(lists == null || lists.length == 0) return null;
//        ListNode res = null;
//        for(int i = 0; i < lists.length; i++) {
//            res = merge(res, lists[i]);
//        }
//        return res;
//    }
//    private ListNode merge(ListNode l1, ListNode l2) {
//        ListNode dummy = new ListNode(-1);
//        ListNode p1 = l1, p2 = l2;
//        ListNode cur = dummy;
//        while(p1 != null && p2 != null) {
//            if(p1.val <= p2.val) {
//                cur.next = p1;
//                p1 = p1.next;
//            } else {
//                cur.next = p2;
//                p2 = p2.next;
//            }
//            cur = cur.next;
//        }
//        if(p1 != null) {
//            cur.next = p1;
//        }
//        if(p2 != null) {
//            cur.next = p2;
//        }
//        return dummy.next;
//    }

    // 优先队列
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        for(ListNode listNode : lists) {
            if(listNode != null) {
                pq.add(listNode);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(!pq.isEmpty()) {
            ListNode poll = pq.poll();
            cur.next = poll;
            cur = cur.next;
            if(poll.next != null) {
                pq.add(poll.next);
            }
        }
        return dummy.next;
    }
}
