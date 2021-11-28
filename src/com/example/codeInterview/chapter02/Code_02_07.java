package com.example.codeInterview.chapter02;

import java.util.Scanner;
import java.util.Stack;

public class Code_02_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Node root = createLinkedList(arr);
        boolean res = isPalindromic(root);
        System.out.println(res);
    }

    private static class Node {
        public int v;
        public Node next;

        public Node(int v) {
            this.v = v;
        }
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

    private static boolean isPalindromic(Node root) {
        if(root == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node tmp = root;
        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
        }
        tmp = root;
        while (tmp != null) {
            if(tmp.v != stack.pop().v) {
                return false;
            }
            tmp = tmp.next;
        }
        return true;
    }
}
