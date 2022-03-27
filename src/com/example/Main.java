package com.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<List<Integer>> lists = new ArrayList<>();
        permutation(new int[]{1, 2, 3, 4}, 0, lists, new ArrayList<>());
        for(List<Integer> list : lists) {
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
    }

    private static void permutation(int[] arr, int st, List<List<Integer>> lists, List<Integer> list) {
        if(st == arr.length) {
            lists.add(new ArrayList<Integer>(list));
        }
        for(int k = st; k < arr.length; k++) {
            swap(arr, k, st);
            list.add(arr[st]);
            permutation(arr, st + 1, lists, list);
            swap(arr, k, st);
            list.remove(list.size() - 1);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
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