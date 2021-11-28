package com.example.codeInterview.chapter02;

import java.util.Scanner;

public class Code_02_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];
        for(int i = 0; i < n1; i++) {
            arr1[i] = scanner.nextInt();
        }
        for(int i = 0; i < n2; i++) {
            arr2[i] = scanner.nextInt();
        }
        Node root1 = createLinkedList(arr1);
        Node root2 = createLinkedList(arr2);
        Node res = addLinkedLists(root1, root2);
        printLinkedList(res);
    }

    private static class Node {
        public int v;
        public Node next;

        public Node(int v) {
            this.v = v;
        }
    }

    private static Node reverseLinkedList(Node root) {
        Node pre = null;
        Node next = null;
        while (root != null) {
            next = root.next;
            root.next = pre;
            pre = root;
            root = next;
        }
        return pre;
    }

    private static Node createLinkedList(int[] arr) {
        if(arr == null || arr.length <= 0) {
            return null;
        }
        Node root = new Node(arr[0]);
        Node tmp = root;
        for(int i = 1; i < arr.length; i++) {
            tmp.next = new Node(arr[i]);
            tmp = tmp.next;
        }
        return root;
    }

    public static Node addLinkedLists(Node root1, Node root2) {
        Node head1 = reverseLinkedList(root1);
        Node head2 = reverseLinkedList(root2);
        Node tmp1 = head1;
        Node tmp2 = head2;
        Node cur = null;
        Node pre = null;
        int v1 = 0;
        int v2 = 0;
        int v = 0;
        int carry = 0;

        while (tmp1 != null || tmp2 != null) {
            v1 = tmp1 == null ? 0 : tmp1.v;
            v2 = tmp2 == null ? 0 : tmp2.v;
            v = v1 + v2 + carry;
            pre = cur;
            cur = new Node(v % 10);
            cur.next = pre;
            carry = v / 10;
            tmp1 = tmp1 == null ? null : tmp1.next;
            tmp2 = tmp2 == null ? null : tmp2.next;
        }
        if(carry == 1) {
            pre = cur;
            cur = new Node(1);
            cur.next = pre;
        }
        reverseLinkedList(head1);
        reverseLinkedList(head2);
        return cur;
    }

    private static void printLinkedList(Node root) {
        Node tmp = root;
        boolean first = true;
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
