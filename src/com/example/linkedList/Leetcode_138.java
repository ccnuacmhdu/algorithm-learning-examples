package com.example.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 关键：不可以创建random节点，random节点本身就是来自现有节点中
 */
public class Leetcode_138 {

    private class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Node t = head;
        Map<Node, Node> map = new HashMap<>();
        while (t != null) {
            map.put(t, new Node(t.val));
            t = t.next;
        }
        t = head;
        while (t != null) {
            map.get(t).next = map.get(t.next);
            map.get(t).random = map.get(t.random);
            t = t.next;
        }
        return map.getOrDefault(head, null);
    }
}
