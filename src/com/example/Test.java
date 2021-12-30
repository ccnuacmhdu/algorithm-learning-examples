package com.example;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        int[] a = {1, 2, 3};
        int[] b = Arrays.copyOfRange(a, 0, a.length);

        Arrays.stream(b).forEach(System.out::println);


        Stack<Integer> stack = new Stack<>();

        LinkedList<Integer> list = new LinkedList<>();
        list.toArray(new Integer[0]);


        Map<String, List<Integer>> map2 = new HashMap<>();


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