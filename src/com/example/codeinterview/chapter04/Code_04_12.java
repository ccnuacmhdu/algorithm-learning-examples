package com.example.codeinterview.chapter04;

import java.util.Scanner;

public class Code_04_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // 暴力递归
//        int fRes = f(a, 0, a.length - 1);
//        int sRes = s(a, 0, a.length - 1);
//        int res = Math.max(fRes, sRes);
//        System.out.println(res);

        // 动态规划
        int res = maxScore(a);
        System.out.println(res);
    }

    // 暴力递归
    /*public static int f(int[] a, int i, int j) {
        if(i == j) {
            return a[i];
        }
        return Math.max(s(a, i+1, j) + a[i], s(a, i, j-1) + a[j]);
    }
    public static int s(int[] a, int i, int j) {
        if(i == j) {
            return 0;
        }
        return Math.min(f(a, i+1, j), f(a, i, j-1));
    }*/

    public static int maxScore(int[] a) {
        int n = a.length;
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];

        for(int i = 0; i < n; i++) {
            f[i][i] = a[i];
//            s[i][i] = 0;
        }
        for(int i = n-2; i >= 0; i--) {
            for(int j = i+1; j < n; j++) {
                s[i][j] = Math.min(f[i+1][j], f[i][j-1]);
                f[i][j] = Math.max(s[i+1][j] + a[i], s[i][j-1] + a[j]);
            }
        }
        return Math.max(f[0][n-1], s[0][n-1]);
    }
}
