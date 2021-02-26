package com.example.codeinterview.chapter02;

import java.util.Scanner;

public class Code_02_01 {
    private static class Node {
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len1 = scanner.nextInt();
        int[] arr1 = new int[len1];
        for(int i = 0; i < len1; i++) {
            arr1[i] = scanner.nextInt();
        }
        int len2 = scanner.nextInt();
        int[] arr2 = new int[len2];
        for(int i = 0; i < arr2.length; i++) {
            arr2[i] = scanner.nextInt();
        }
        Node root1 = buildLinkedList(arr1);
        Node root2 = buildLinkedList(arr2);
        String res = getCommonPart(root1, root2);
        System.out.println(res);
    }

    public static String getCommonPart(Node root1, Node root2) {
        StringBuilder sb = new StringBuilder();
        while (root1 != null && root2 != null) {
            if(root1.value == root2.value) {
                sb.append(root1.value).append(" ");
                root1 = root1.next;
                root2 = root2.next;
            } else if(root1.value < root2.value) {
                root1 = root1.next;
            } else {
                root2 = root2.next;
            }
        }
        if(sb.length() == 0) {
            return "";
        }
        return sb.substring(0, sb.length()-1);
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
}
