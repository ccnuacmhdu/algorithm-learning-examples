package com.example.linkedList;

public class Leetcode_148 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

//    // 插入排序
//    public ListNode sortList(ListNode head) {
//        if(head == null) return head;
//        ListNode dummy = new ListNode(-1);
//        dummy.next = head;
//        ListNode cur = head.next;
//        ListNode lastSortedNode = head;
//        while(cur != null) {
//            if(cur.val >= lastSortedNode.val) {
//                lastSortedNode = cur;
//            } else {
//                ListNode t = dummy.next;    // 重要，不能取 t = head，因为 head 可能变化了
//                ListNode pre = dummy;   // 避免 pre = null
//                while(cur.val >= t.val) {
//                    pre = t;
//                    t = t.next;
//                }
//                lastSortedNode.next = cur.next;
//                cur.next = t;
//                pre.next = cur;
//            }
//            cur = lastSortedNode.next;
//        }
//        return dummy.next;
//    }

    // 归并排序
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }
    private ListNode sortList(ListNode from, ListNode to) {
        // 0 个节点
        if(from == null) {
            return from;
        }
        // 1 个节点（不好理解，需要举例，比如2节点链表，3节点链表，关键问题是null）
        if(from.next == to) {
            from.next = null;
            return from;
        }

        ListNode fast = from;
        ListNode slow = from;

        while(fast != to && fast.next != to) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode left = sortList(from, slow);
        ListNode right = sortList(slow, to);
        return merge(left, right);
    }
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode t = dummy;
        while(left != null && right != null) {
            if(left.val <= right.val) {
                t.next = left;
                left = left.next;
            } else {
                t.next = right;
                right = right.next;
            }
            t = t.next;
        }
        // 注意是 if，不是 while
        if(left != null) {
            t.next = left;
        }
        // 注意是 if，不是 while
        if(right != null) {
            t.next = right;
        }
        return dummy.next;
    }

}
