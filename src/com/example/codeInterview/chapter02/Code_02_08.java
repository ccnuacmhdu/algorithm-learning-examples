package com.example.codeInterview.chapter02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Code_02_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int pivot = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Node root1 = createLinkedList(arr);
        Node root2 = linkedListPartition2(root1, pivot);
        printLinkedList(root2);
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

    private static void swap(List<Node> nodeList, int x, int y) {
        Node tmp = nodeList.get(x);
        nodeList.set(x, nodeList.get(y));
        nodeList.set(y, tmp);
    }

    private static void listPartition(List<Node> nodeList, int pivot) {
        int left = -1;
        int right = nodeList.size();
        int curIndex = 0;
        while (curIndex != right) {
            if(nodeList.get(curIndex).v < pivot) {
                swap(nodeList, curIndex++, ++left);
            } else if(nodeList.get(curIndex).v == pivot) {
                curIndex++;
            } else {
                swap(nodeList, curIndex, --right);
            }
        }
    }

    private static Node linkedListPartition(Node root, int pivot) {
        if(root == null) {
            return root;
        }
        List<Node> nodeList = new ArrayList<>();
        while (root != null) {
            nodeList.add(root);
            root = root.next;
        }
        listPartition(nodeList, pivot);
        for(int i = 1; i < nodeList.size(); i++) {
            nodeList.get(i-1).next = nodeList.get(i);
        }
        nodeList.get(nodeList.size()-1).next = null;
        return nodeList.get(0);
    }

    // 保证原始顺序（小于区、等于区、大于区分别进入三个链表，再合并即可）
    private static Node linkedListPartition2(Node root, int pivot) {
        Node sH = null; // 小于区头
        Node sT = null; // 小于区尾
        Node eH = null; // 等于区头
        Node eT = null; // 等于区尾
        Node bH = null; // 大于区头
        Node bT = null; // 大于区尾

        Node next = null;
        while (root != null) {
            next = root.next;
            root.next = null;
            if(root.v < pivot) {
                if(sH == null) {
                    sH = root;
                    sT = root;
                } else {
                    sT.next = root;
                    sT = root;
                }
            } else if(root.v == pivot) {
                if(eH == null) {
                    eH = root;
                    eT = root;
                } else {
                    eT.next = root;
                    eT = root;
                }
            } else {
                if(bH == null) {
                    bH = root;
                    bT = root;
                } else {
                    bT.next = root;
                    bT = root;
                }
            }
            root = next;
        }
        // 注意了，sT如果是null说明小于区不存在，eT/bT类似
        if(sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }

        if(eT != null) {
            eT.next = bH;
        }

        return sH != null ? sH : eH != null ? eH : bH;
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
