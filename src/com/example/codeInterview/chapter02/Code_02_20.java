package com.example.codeInterview.chapter02;

import java.util.Scanner;

public class Code_02_20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Node root = createCircleLinkedList(arr);
        Node newRoot = merge(root);
        printLinkedList(newRoot);
    }

    private static class Node {
        public int v;
        public Node next;

        public Node(int v) {
            this.v = v;
            next = null;
        }
    }

    public static Node merge(Node root) {
        if(root == null || root.next == null || root.next.next == null) {
            return root;
        }
        Node fast = root.next;
        Node slow = root;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node latter = slow.next;
        slow.next = null;
        merge(root, latter);
        return root;
    }

    private static void merge(Node former, Node latter) {
        Node next = null;
        while (former.next != null) {
            next = latter.next;
            latter.next = former.next;
            former.next = latter;
            former = latter.next;
            latter = next;
        }
        former.next = latter;
    }

    private static int linkedListLength(Node root) {
        Node cur = root;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
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
