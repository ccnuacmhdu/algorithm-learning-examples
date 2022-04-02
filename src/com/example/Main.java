package com.example;

import sun.nio.cs.ext.MacHebrew;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            int ret = lev(s1, s2);
            System.out.println(ret);
        }
    }
    private static int lev(String s1, String s2) {
        char[] cs1 = ("" + s1).toCharArray();
        char[] cs2 = ("" + s2).toCharArray();
        int n1 = cs1.length;
        int n2 = cs2.length;
        int[][] dp = new int[n1][n2];
        for(int i = 0; i < n1; i++) {
            dp[i][0] = i;
        }
        for(int j = 0; j < n2; j++) {
            dp[0][j] = j;
        }
        for(int i = 1; i < n1; i++) {
            for(int j = 1; j < n2; j++) {
                if(cs1[i] == cs2[j]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
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