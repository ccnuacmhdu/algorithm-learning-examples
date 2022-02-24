package com.example.linkedList;

/**
 * K 个一组翻转链表
 */
public class Leetcode_25 {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode t = head;
        ListNode from = null, to = null, pre = null;
        int len = getLen(head);
        int n = (len % k == 0) ? (len / k) : (len / k + 1);
        ListNode[][] arr = new ListNode[n][2];
        int idx = 0;
        while(t != null) {
            from = t;
            int cnt = k;
            while(cnt > 0 && t != null) {
                cnt--;
                pre = t;
                t = t.next;
            }
            to = pre;
            if(cnt == 0) {
                reverse(from, to);
                arr[idx][0] = to;
                arr[idx][1] = from;
            } else {
                arr[idx][0] = from;
                arr[idx][1] = to;
            }
            idx++;
        }
        for(int i = 0; i < n - 1; i++) {
            arr[i][1].next = arr[i + 1][0];
        }
        return arr[0][0];
    }
    private int getLen(ListNode node) {
        int len = 0;
        ListNode t = node;
        while(t != null) {
            len++;
            t = t.next;
        }
        return len;
    }
    private void reverse(ListNode from, ListNode to) {
        ListNode t = from;
        ListNode pre = null;
        ListNode next = null;
        while(t != to) {
            next = t.next;
            t.next = pre;
            pre = t;
            t = next;
        }
        to.next = pre;
    }
}
