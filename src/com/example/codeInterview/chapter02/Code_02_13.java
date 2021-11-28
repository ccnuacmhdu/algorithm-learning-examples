package com.example.codeInterview.chapter02;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Code_02_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Node root = createLinkedList(arr);
        removeRep(root);
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

    public static void removeRep(Node root) {
        Node cur = root.next;
        Node pre = root;
        Set<Integer> set = new HashSet<>();
        set.add(pre.v);
        while (cur != null) {
            if(set.contains(cur.v)) {
                pre.next = cur.next;
            } else {
                set.add(cur.v);
                pre = cur;
            }
            cur = cur.next;
        }
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
