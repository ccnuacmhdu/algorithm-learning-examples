package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            int[] cnt = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            int maxW = 0;
            for(int i = 0; i < n; i++) {
                cnt[i] = scanner.nextInt();
                maxW += a[i] * cnt[i];
            }
            boolean[][] dp = new boolean[n][maxW + 1];
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j <= maxW; j++) {
                dp[0][j] = (j % a[0] == 0 && j <= a[0] * cnt[0]) ? true : false;
                if(dp[0][j]) set.add(j);
            }
            for(int i = 1; i < n; i++) {
                for(int j = 0; j <= maxW; j++) {
                    for(int k = 0; k <= cnt[i] && j >= a[i] * k; k++) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j - k * a[i]];
                        if(dp[i][j]) {
                            set.add(j);
                        }
                    }
                }
            }
            System.out.println(set.size());
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