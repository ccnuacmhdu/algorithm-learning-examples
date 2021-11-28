package com.example.codeInterview.chapter02;

import java.util.Scanner;

public class Code_02_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        Node root = createLinkedList(arr);
        root = removeValue(root, m);
        printLinkedList(root);
    }

    private static class Node {
        public int v;
        public Node next;

        public Node(int v) {
            this.v = v;
            next = null;
        }
    }

    public static Node removeValue(Node root, int m) {
        if(root == null) {
            return root;
        }
        Node pre = null;
        Node cur = root;
        while (cur != null) {
            if(cur.v == m) {
                if(pre != null) {
                    pre.next = cur.next;
                } else {
                    // 本函数需要返回root，否则输入arr(1,2,3,4) m=1会得到输出1 2 3 4
                    root = cur.next;
                }
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return root;
    }

    private static Node createLinkedList(int[] arr) {
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
        return root;
    }

    private static void printLinkedList(Node root) {
        boolean first = true;
        Node tmp = root;
        while (tmp != null) {
            if(first) {
                System.out.print(tmp.v);
                first = false;
            } else {
                System.out.print(" " + tmp.v);
            }
            tmp = tmp.next;
        }
        System.out.println();
    }
}
