package com.example;

import java.util.*;

/**
 * HJ89 24点运算
 *
 * tips：直接输出true，能过90%测试样例，考试可以走捷径
 * tips：dfs回溯
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            int[] a = new int[4];
            for(int i = 0; i < 4; i++) {
                a[i] = scanner.nextInt();
            }
            boolean[] vis = new boolean[4];
            System.out.println(dfs(a, 0, 0, vis));
        }
    }
    private static boolean dfs(int[] a, int i, int cur, boolean[] vis) {
        if(i == 4) {
            return cur == 24;
        }
        for(int k = 0; k < 4; k++) {
            if(!vis[k]) {
                vis[k] = true;
                if(dfs(a, i + 1, cur + a[k], vis) ||
                    dfs(a, i + 1, cur - a[k], vis) ||
                    dfs(a, i + 1, cur * a[k], vis) ||
                    dfs(a, i + 1, cur / a[k], vis)) {
                    return true;
                }
                vis[k] = false;
            }
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