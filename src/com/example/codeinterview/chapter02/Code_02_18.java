package com.example.codeinterview.chapter02;

import java.util.Scanner;

public class Code_02_18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int num = scanner.nextInt();
        Node root = createCircleLinkedList(arr);
        Node newRoot = insertNum(root, num);
        printCircleLinkedList(newRoot);
    }

    private static class Node {
        public int v;
        public Node next;

        public Node(int v) {
            this.v = v;
            next = null;
        }
    }

    public static Node insertNum(Node root, int num) {
        Node node = new Node(num);
        if(root == null) {
            node.next = node;
            return node;
        }
        Node pre = root;
        Node cur = root.next;
        while (cur != root) {
            if(pre.v <= num && num <= cur.v) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;
        node.next = cur;
        return root.v <= num ? root : node;
    }

    private static Node createCircleLinkedList(int[] arr) {
        if(arr == null || arr.length <= 0) {
            return null;
        }
        Node root = new Node(arr[0]);
        Node tmp = root;
        for(int i = 1; i < arr.length; i++) {
            Node next = new Node(arr[i]);
            tmp.next = next;
            tmp = next;
        }
        tmp.next = root;
        return root;
    }

    private static void printCircleLinkedList(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.v);
        Node tmp = root.next;
        while (tmp != root) {
            System.out.print(" " + tmp.v);
            tmp = tmp.next;
        }
        System.out.println();
    }
}
