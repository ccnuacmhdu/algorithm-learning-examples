package com.example.codeinterview.chapter02;

import java.util.HashMap;
import java.util.Map;

public class Code_02_09 {
    private class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if(head == null) {
            return head;
        }
        Node tmp1 = head.next;
        Node root = new Node(head.val);
        Node tmp2 = root;
        Map<Node, Node> map = new HashMap<>();
        map.put(head, root);
        while(tmp1 != null) {
            tmp2.next = new Node(tmp1.val);
            tmp2 = tmp2.next;
            map.put(tmp1, tmp2);
            tmp1 = tmp1.next;
        }
        tmp1 = head;
        tmp2 = root;
        while(tmp1 != null) {
            tmp2.random = map.get(tmp1.random);
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
        }
        return root;
    }

    public Node copyRandomList2(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node tmp = head;
        while (tmp != null) {
            map.put(tmp, new Node(tmp.val));
            tmp = tmp.next;
        }
        tmp = head;
        while (tmp != null) {
            map.get(tmp).next = map.get(tmp.next);
            map.get(tmp).random = map.get(tmp.random);
            tmp = tmp.next;
        }
        return map.get(head);
    }
}
