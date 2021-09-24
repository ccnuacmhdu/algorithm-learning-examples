package com.example.leetcode100hot;

/**
 * 链表排序
 */
public class Leetcode_148 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 归并排序
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }
    private ListNode sortList(ListNode from, ListNode to) {
        if(from == null) return from;
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
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(left != null && right != null) {
            if(left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if(left != null) {
            cur.next = left;
        } else {
            cur.next = right;
        }
        return dummy.next;
    }

    // 插入排序
    public ListNode sortList2(ListNode head) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode lastSorted = head;
        ListNode cur = head.next;
        while(cur != null) {
            if(cur.val >= lastSorted.val) {
                lastSorted = cur;
            } else {
                ListNode t = dummy.next;
                ListNode pre = dummy;
                while(cur.val >= t.val) {
                    pre = t;
                    t = t.next;
                }
                lastSorted.next = cur.next;
                pre.next = cur;
                cur.next = t;
            }
            cur = lastSorted.next;
        }
        return dummy.next;
    }
}
