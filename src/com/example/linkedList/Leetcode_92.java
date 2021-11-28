package com.example.linkedList;

public class Leetcode_92 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // v2
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode rightNode = null;
        ListNode rightNextNode = null;
        ListNode leftPreNode = dummy;

        for(int i = 1; i < left; i++) {
            leftPreNode = leftPreNode.next;
        }

        rightNode = leftPreNode;
        for(int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        rightNextNode = rightNode.next;

        rightNode.next = null;
        reverseLinkedList(leftPreNode.next);

        leftPreNode.next.next = rightNextNode;
        leftPreNode.next = rightNode;

        return dummy.next;
    }
    private void reverseLinkedList(ListNode root) {
        ListNode cur = root;
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    // v1
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//        ListNode t = head, next = null, pre = null;
//        ListNode finalPre = null, finalRight = null;
//        int loc = 1;
//        while (t != null) {
//            if(loc == left) {
//                finalPre = pre;
//                finalRight = t;
//                while (loc != right) {
//                    next = t.next;
//                    t.next = pre;
//                    pre = t;
//                    t = next;
//                    loc++;
//                }
//                next = t.next;
//                t.next = pre;
//                finalRight.next = next;
//                if(finalPre == null) return t;  //
//                finalPre.next = t;
//                break;  //
//            } else {
//                next = t.next;
//                pre = t;
//                t = next;
//                loc++;
//            }
//        }
//        return head;
//    }
}
