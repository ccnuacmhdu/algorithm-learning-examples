package com.example.hj;

import java.util.*;

/**
 * HJ41 称砝码
 *
 * tips：转为01背包问题，判定可以达到哪些值
 */
public class HJ41 {
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


// 超时（暴力dfs）
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            int n = scanner.nextInt();
//            int[] a = new int[n];
//            int[] cnt = new int[n];
//            for(int i = 0; i < n; i++) {
//                a[i] = scanner.nextInt();
//            }
//            for(int i = 0; i < n; i++) {
//                cnt[i] = scanner.nextInt();
//            }
//            Set<Integer> set = new HashSet<>();
//            dfs(a, cnt, 0, 0, set);
//            System.out.println(set.size());
//        }
//    }
//    private static void dfs(int[] a, int[] cnt, int i, int cur, Set<Integer> set) {
//        if(i == a.length) {
//            return;
//        }
//        set.add(cur);
//        for(int k = 0; k <= cnt[i]; k++) {
//            int num = cur + k * a[i];
//            set.add(num);
//            dfs(a, cnt, i + 1, num, set);
//        }
//    }
//}