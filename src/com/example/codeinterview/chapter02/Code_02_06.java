package com.example.codeinterview.chapter02;

import java.util.Scanner;

public class Code_02_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Node root = createLinkedList(n);
        Node last = kill(root, m);
        System.out.println(last.getV());
    }

    private static class Node {
        private int v;
        private Node next;

        public Node(int v) {
            this.v = v;
        }

        public int getV() {
            return v;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private static Node createLinkedList(int n) {
        if(n <= 0) {
            return null;
        }
        Node root = new Node(1);
        Node tmp = root;
        for(int i = 2; i <= n; i++) {
            Node next = new Node(i);
            tmp.setNext(next);
            tmp = next;
        }
        tmp.setNext(root);
        return root;
    }

    private static Node kill(Node root, int m) {
        if(root == null || m <= 0 || root.next == root) {
            return root;
        }

        Node last = root;
        while (last.getNext() != root) {
            last = last.getNext();
        }
        int cnt = 0;
        Node pre = last;
        Node cur = root;
        while (pre != cur) {
            if(++cnt == m) {
                pre.setNext(cur.getNext());
                cur = cur.getNext();
                cnt = 0;
            } else {
                pre = cur;
                cur = cur.getNext();
            }
        }
        return cur;
    }
}
