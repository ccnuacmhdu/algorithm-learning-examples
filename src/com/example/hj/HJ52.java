package com.example.hj;

import java.util.*;

/**
 * HJ52 计算字符串的编辑距离
 *
 * 1 dp[i][j]用来表示字符串a的[1...i]和字符串b[1...j]的levenshtein距离；
 * 2 插入和删除操作互为逆过程：a删除指定字符变b等同于b插入指定字符变a；
 * 3 如果a[i] == b[j]，则说明a[i]和b[j]分别加入a，b之后不会影响levenshtein距离，dp[i][j] = dp[i-1][j-1];
 * 4 如果a[i] != b[j]，则需要考虑3种情况的可能：
 *  a中插入字符，dp[i][j] = dp[i-1][j] + 1;
 *  b中插入字符，dp[i][j] = dp[i][j-1] + 1;
 *  a[i]替换成b[j]，dp[i][j] = dp[i-1][j-1] + 1;
 *
 */
public class HJ52 {
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
        char[] cs1 = (" " + s1).toCharArray();
        char[] cs2 = (" " + s2).toCharArray();
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
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[n1 - 1][n2 - 1];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
