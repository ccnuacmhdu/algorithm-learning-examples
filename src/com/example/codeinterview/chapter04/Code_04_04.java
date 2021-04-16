package com.example.codeinterview.chapter04;

import java.util.Arrays;
import java.util.Scanner;

public class Code_04_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int p = scanner.nextInt();
        if(n < 2 || k < 0 || m < 1 || m > n || p < 1 || p > n) {
            System.out.println(0);
            return;
        }
//        int[][] dp = new int[k+1][n+1];
//        for(int i = 0; i <= k; i++) {
//            Arrays.fill(dp[i], -1);
//        }
//        int res = walk(n, m, k, p, dp);
        int res = walk(n, m, k, p);
        System.out.println(res);
    }
    // 记忆化搜索（思路：从m走k步到p，下一步只能走m-1或m+1并再走k-1步到p）
    public static int walk(int n, int m, int k, int p, int[][] dp) {
        if(k == 0) {
            dp[k][m] = (m == p ? 1 : 0);
            return dp[k][m];
        }
        if(dp[k][m] != -1) {
            return dp[k][m];
        }
        if(m == 1) {
            return dp[k][m] = walk(n, 2, k - 1, p, dp) % 1000000007;
        }
        if(m == n) {
            return dp[k][m] = walk(n, n - 1, k - 1, p, dp) % 1000000007;
        }
        return dp[k][m] = (walk(n, m - 1, k - 1, p, dp) % 1000000007 + walk(n, m + 1, k - 1, p, dp) % 1000000007) % 1000000007;
    }

    // 动态规划
//    public static int walk(int n, int m, int k, int p) {
//        int[][] dp = new int[k+1][n+2];
//        final int MOD = 1000000007;
//
//        dp[0][p] = 1;
//        for(int i = 1; i <= k; i++) {
//            for(int j = 1; j <= n; j++) {
//                dp[i][j] = (dp[i-1][j-1] % MOD + dp[i-1][j+1] % MOD) % MOD;
//            }
//        }
//
//        return dp[k][m] % MOD;
//    }

    // 动态规划+状态压缩
    public static int walk(int n, int m, int k, int p) {
        int[] dp = new int[n+2];
        final int MOD = 1000000007;

        dp[p] = 1;
        int tmp;
        int left = dp[0];
        for(int i = 1; i <= k; i++) {
            for(int j = 1; j <= n; j++) {
                tmp = dp[j];
                dp[j] = (left % MOD + dp[j+1] % MOD) % MOD;
                left = tmp;
            }
        }

        return dp[m] % MOD;
    }

}
