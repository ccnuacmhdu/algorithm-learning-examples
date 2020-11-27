package com.example.codeinterview.chapter02;

import java.util.Scanner;

public class Code_02_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr1 = new int[n];
        for(int i = 0; i < n; i++) {
            arr1[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] arr2 = new int[m];
        for(int i = 0; i < m; i++) {
            arr2[i] = scanner.nextInt();
        }
        Node root1 = buildLinkedList(arr1);
        Node root2 = buildDLinkedList(arr2);

        Node reverseRoot1 = reverseLinkedList(root1);
        Node reverseRoot2 = reverseDLinkedList(root2);

        String res1 = getRes(reverseRoot1);
        String res2 = getRes(reverseRoot2);

        System.out.println(res1);
        System.out.println(res2);
    }

    private static class Node {
        public int value;
        public Node next;
        public Node last;
        public Node(int value) {
            this.value = value;
        }
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

    private static Node buildDLinkedList(int[] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        Node root = new Node(arr[0]);
        Node cur = root;
        Node last = null;

        for(int i = 1; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur.next.last = cur;
            last = cur;
            cur = cur.next;
        }

        return root;
    }

    private static String getRes(Node root) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (root != null) {
            if(first) {
                sb.append(root.value);
                first = false;
            } else {
                sb.append(" ");
                sb.append(root.value);
            }
            root = root.next;
        }
        return sb.toString();
    }

    private static Node reverseLinkedList(Node root) {
        Node cur = root;
        Node pre = null;
        Node tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    private static Node reverseDLinkedList(Node root) {
        Node cur = root;
        Node last = null;
        Node tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = last;
            cur.last = cur.next;
            last = cur;
            cur = tmp;
        }
        return last;
    }
}
