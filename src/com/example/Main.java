package com.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            int[] dp1 = longestIncSub(arr);
            int[] dp2 = longestDecSub(arr);
            int max = 0;
            for(int i = 0; i < n; i++) {
                max = Math.max(dp1[i] + dp2[i] - 1, max);
            }
            System.out.println(n - max);
        }
    }

    private static int[] longestIncSub(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for(int i = 1; i < arr.length; i++) {
            int max = 1;
            for(int j = 0; j < i; j++) {
                max = arr[j] < arr[i] ? Math.max(dp[j] + 1, max) : max;
            }
            dp[i] = max;
        }
        return dp;
    }

    private static int[] longestDecSub(int[] arr) {
        int[] dp = new int[arr.length];
        dp[arr.length - 1] = 1;
        for(int i = arr.length - 2; i >= 0; i--) {
            int max = 1;
            for(int j = i + 1; j < arr.length; j++) {
                max = arr[i] > arr[j] ? Math.max(dp[j] + 1, max) : max;
            }
            dp[i] = max;
        }
        return dp;
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