package com.example;

import java.util.*;

public class Test {
    private static class Node {
        String name;
        int score;
        int id;

        public Node(String name, int score, int id) {
            this.name = name;
            this.score = score;
            this.id = id;
        }
    }


    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        int[] a2 = list.toArray(new int[0]);

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sortWay = scanner.nextInt();
        Node[] nodes = new Node[n];
        scanner.nextLine();
        for(int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String[] splits = s.split(" ");
            nodes[i] = new Node(splits[0], Integer.valueOf(splits[1]), i + 1);
        }
        sort(nodes, sortWay);
        print(nodes);
    }

    private static void sort(Node[] nodes, int sortWay) {
        if(sortWay == 0) {
            Arrays.sort(nodes, new Comparator<Node>(){
                public int compare(Node n1, Node n2) {
                    if(n1.score < n2.score) {
                        return n1.score - n2.score;
                    } else {
                        return n1.id - n2.id;
                    }
                }
            });
        } else {
            Arrays.sort(nodes, new Comparator<Node>(){
                public int compare(Node n1, Node n2) {
                    if(n1.score < n2.score) {
                        return n2.score - n1.score;
                    } else {
                        return n1.id - n2.id;
                    }
                }
            });
        }
    }

    private static void print(Node[] nodes) {
        for(Node node : nodes) {
            System.out.printf("%s %d\n", node.name, node.score);
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}