package com.example.codeinterview.chapter02;

// [Leetcode] 147. 对链表进行插入排序
public class Code_02_16 {

    private class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode lastSorted = head; // 链表已排序好的前一部分最后一个节点
        ListNode cur = head.next;
        ListNode newHead = new ListNode(-1);    // 引入newHead，方便在head前插入节点
        newHead.next = head;

        while (cur != null) {
            if(cur.val >= lastSorted.val) { // 大于等于lastSorted，直接插在lastSorted后即可（更新lastSorted即可）
                lastSorted = lastSorted.next;
            } else {    // 从头遍历，找到插入点，插入点比在lastSorted之前
                ListNode tmp = newHead;
                while (cur.val >= tmp.next.val) {
                    tmp = tmp.next;
                }
                // 下面三步画图容易理解。cur的插入点必定在lastSorted之前，cur走之前，lastSorted的next要更新成cur.next
                lastSorted.next = cur.next;
                cur.next = tmp.next;
                tmp.next = cur;
            }
            cur = lastSorted.next;  // 更新cur
        }
        return newHead.next;
    }

}
