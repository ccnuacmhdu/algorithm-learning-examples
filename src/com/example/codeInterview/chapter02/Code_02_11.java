package com.example.codeInterview.chapter02;

import java.util.HashSet;
import java.util.Set;

/**
 * 两个单链表相交的一系列问题
 *
 * 【分析】画图分析可知，相交只有两种情况，
 * 情况1：两个无环链表相交
 * 情况2：两个有环链表相交
 *
 * 【找入环节点-哈希表法】
 * 【找入环节点-快慢指针法】
 * 1．设置一个慢指针 slow 和一个快指针 fast。在开始时，slow 和 fast 都指向链表的头节点
 * head。然后 slow 每次移动一步，fast 每次移动两步，在链表中遍历起来。
 * 2．如果链表无环，那么 fast 指针在移动过程中一定先遇到终点，一旦 fast 到达终点，说明
 * 链表是没有环的，直接返回 null，表示该链表无环，当然也没有第一个入环的节点。
 * 3．如果链表有环，那么 fast 指针和 slow 指针一定会在环中的某个位置相遇，当 fast 和 slow
 * 相遇时，fast 指针重新回到 head 的位置，slow 指针不动。接下来，fast 指针从每次移动两步改
 * 为每次移动一步，slow 指针依然每次移动一步，然后继续遍历。
 * 4．fast 指针和 slow 指针一定会再次相遇，并且在第一个入环的节点处相遇。
 *
 * 【入环节点证明】
 * 首先，明显在环上某节点相遇。设链表头到环入口长度为a，环入口到相遇点长度为b，相遇点到环入口长度为c，
 * 相遇时快指针路程=a+(b+c)k+b，k>=1，其中b+c为环的长度，k为绕环的圈数，慢指针路程=a+b，快指针
 * 走的路程是慢指针的两倍，所以，（a+b）*2=a+(b+c)k+b，化简可得，a=(k-1)(b+c)+c，这个式子的
 * 意思是链表头到环入口的距离=相遇点到环入口的距离+（k-1）圈环长度。其中k>=1,所以k-1>=0圈。所以
 * 两个指针分别从链表头和相遇点出发，最后一定相遇于环入口。
 *
 * 【两无环链表是否相交问题】
 * 1．链表 1 从头节点开始，走到最后一个节点（不是结束），统计链表 1 的长度记为 len1，
 * 同时记录链表 1 的最后一个节点记为 end1。
 * 2．链表 2 从头节点开始，走到最后一个节点（不是结束），统计链表 2 的长度记为 len2，
 * 同时记录链表 2 的最后一个节点记为 end2。
 * 3．如果 end1!=end2，说明两个链表不相交，返回 null 即可；如果 end==end2，说明两个链
 * 表相交，进入步骤 4 来找寻第一个相交节点。
 * 4．如果链表 1 比较长，链表 1 就先走 len1−len2 步；如果链表 2 比较长，链表 2 就先走
 * len2−len1 步。然后两个链表一起走，一起走的过程中，两个链表第一次走到一起的那个节点就
 * 是第一个相交的节点。
 *
 * 【两有环链表是否相交问题】
 * 假设链表 1 的第一个入环节点记为 loop1，链表 2 的第一个入环节点记为 loop2。
 * 1．如果 loop1==loop2，我们只要考虑链表1从头开始到 loop1 这一段与链表 2 从头开始到 loop2 这
 * 一段，在那里第一次相交即可，而不用考虑进环该怎么处理，这就与两无环链表相交问题类似，只不过后者
 * 是把 null 作为一个链表的终点，而这里是把 loop1(loop2) 作为链表的终点。
 * 2．如果 loop1!=loop2，让链表 1 从 loop1 出发，因为 loop1 和之后的所有节点都在环上，所以将来
 * 一定能回到 loop1。如果回到 loop1 之前并没有遇到 loop2，就不相交，直接返回 null；如果回到 loop1
 * 之前遇到了 loop2，就相交。因为 loop1 和 loop2 都在两条链表上，只不过 loop1 是离链表1 较近的节点，
 * loop2 是离链表2 较近的节点。所以，此时返回 loop1 或 loop2 都可以。
 */
public class Code_02_11 {
    private static class Node {
        public int val;
        public Node next;

        public Node(int x) {
            val = x;
        }
    }

    /**
     * 【找入环节点-哈希表法】
     *
     * @param head
     * @return
     */
    public static Node getLoopNode1(Node head) {
        Set<Node> set = new HashSet<>();
        Node tmp = head;
        while (tmp != null) {
            if(set.contains(tmp)) {
                return tmp;
            }
            set.add(tmp);
            tmp = tmp.next;
        }
        return null;
    }

    /**
     * 【找入环节点-快慢指针法】
     *
     * @param head
     * @return
     */
    public static Node getLoopNode2(Node head) {
        // 小于三个节点不可能成环
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if(fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 【两无环链表相交问题】
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if(head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            cur1 = cur1.next;
            n++;
        }
        while (cur2.next != null) {
            cur2 = cur2.next;
            n--;
        }
        if(cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 【两有环链表相交问题】
     *
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    /**
     * 【两个单链表相交的一系列问题】
     *
     * @param head1
     * @param head2
     * @return
     */
    public Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode2(head1);
        Node loop2 = getLoopNode2(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }
}
