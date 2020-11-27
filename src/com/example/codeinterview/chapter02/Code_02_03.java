package com.example.codeinterview.chapter02;

import java.util.Scanner;

public class Code_02_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Node root = buildLinkedList(arr);
        Node resNode = deleteKthNode(root, k);
        String res = getPrintRes(resNode);
        System.out.println(res);
    }

    private static class Node {
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    private static Node buildLinkedList(int[] arr) {
        Node root = null;
        Node tmp = null;
        boolean first = true;
        int i = 0;
        while (i < arr.length) {
            if(first) {
                root = new Node(arr[i]);
                tmp = root;
                first = false;
            } else {
                tmp.next = new Node(arr[i]);
                tmp = tmp.next;
            }
            i++;
        }
        return root;
    }

    private static String getPrintRes(Node root) {
        StringBuilder sb = new StringBuilder();
        while (root != null) {
            sb.append(root.value).append(" ");
            root = root.next;
        }
        if(sb.length() == 0) {
            return "";
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static Node deleteKthNode(Node root, int kth) {
        int len = 0;
        Node tmp = root;
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        if(kth <= 0 || kth > len) {
            return root;
        }
        Node pre = root;
        Node cur = root;
        int i = 1;
        while (cur != null) {
            if(i == kth) {
                pre.next = cur.next;
                cur = null;
                break;
            }
            pre = cur;
            cur = cur.next;
            i++;
        }
        return root;
    }
}
