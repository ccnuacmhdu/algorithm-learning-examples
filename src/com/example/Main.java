package com.example;

import sun.nio.cs.ext.MacHebrew;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String regex = sc.nextLine().toLowerCase();
            String s = sc.nextLine().toLowerCase();
            boolean[][] dp = new boolean[regex.length() + 1][s.length() + 1];
            dp[0][0] = true;
            for(int i = 0; i < regex.length(); i++) {
                if (regex.charAt(i) == '*') {
                    dp[i + 1][0] = true;
                } else {
                    break;
                }
            }
            for(int i = 1; i <= regex.length(); i++) {
                char r = regex.charAt(i - 1);
                for(int j = 1; j <= s.length(); j++) {
                    char c = s.charAt(j - 1);
                    if(r == '?') {
                        dp[i][j] = check(c) ? dp[i - 1][j - 1] : false;
                    } else if(r == '*') {
                        dp[i][j] = check(c) ? (dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1]) : false;
                    } else if(r == c) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
            System.out.println(dp[regex.length()][s.length()]);
        }
    }
    private static boolean check(char c) {
        if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
            return true;
        }
        return false;
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