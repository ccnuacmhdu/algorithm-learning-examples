package com.example.codeinterview.chapter02;

import java.util.Scanner;

public class Code_02_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        Node root = buildLinkedList(arr);
        Node node = reversePart(root, from, to);
        printLinkedList(node);
    }

    private static class Node {
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    private static Node reversePart(Node root, int from, int to) {
        int len = length(root);
        if(from < 1 || from > len || to < 1 || to > len || from > to) {
            return root;
        }

        Node tmp = root;
        Node last = null;
        Node fromLastNode = null;
        Node fromNode = null;
        Node toNode = null;
        Node toNextNode = null;
        int cnt = 0;
        while (tmp != null) {
            cnt++;
            if(cnt == from) {
                fromLastNode = last;
                fromNode = tmp;
            }
            if(cnt == to) {
                toNode = tmp;
                toNextNode = tmp.next;
            }
            last = tmp;
            tmp = tmp.next;
        }

        tmp = fromNode;
        last = fromLastNode;
        while (tmp != toNextNode) {
            Node next = tmp.next;
            tmp.next = last;
            last = tmp;
            tmp = next;
        }

        if(from == 1) {
            fromNode.next = toNextNode;
            return toNode;
        } else {
            fromLastNode.next = toNode;
            fromNode.next = toNextNode;
            return root;
        }
    }

    private static int length(Node root) {
        Node tmp = root;
        int len = 0;
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        return len;
    }

    private static Node buildLinkedList(int[] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        Node root = new Node(arr[0]);
        Node cur = root;
        for(int i = 1; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
        }
        return root;
    }

    private static void printLinkedList(Node root) {
        Node tmp = root;
        boolean first = true;
        while (tmp != null) {
            if(first) {
                System.out.print(tmp.value);
                first = false;
            } else {
                System.out.print(" " + tmp.value);
            }
            tmp = tmp.next;
        }
        System.out.println();
    }
}
