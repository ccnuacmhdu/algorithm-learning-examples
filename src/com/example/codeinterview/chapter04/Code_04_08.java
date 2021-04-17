package com.example.codeinterview.chapter04;


import java.util.Scanner;

public class Code_04_08 {
    // [leetcode] 1143. 最长公共子序列
    /*public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1][len2];

        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for(int i = 1; i < len1; i++) {
            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : Math.max(dp[i-1][0], 0);
        }
        for(int j = 1; j < len2; j++) {
            dp[0][j] = text1.charAt(0) == text2.charAt(j) ? 1 : Math.max(dp[0][j-1], 0);
        }
        for(int i = 1; i < len1; i++) {
            for(int j = 1;  j < len2; j++) {
                if(text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[len1-1][len2-1];
    }*/


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();
        String res = getLongestCommonSubsequence(s1, s2);
        System.out.println(res);
    }
    // 得到最长公共子序列字符串
    public static String getLongestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return "-1";
        }
        char[] chs1 = text1.toCharArray();
        char[] chs2 = text2.toCharArray();
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        int[][] dp = LCS(text1, text2);
        if(dp[m][n] == 0) {
            return "-1";
        }
        char[] res = new char[dp[m][n]];
        int index = dp[m][n] - 1;
        while (index >= 0) {
            if(m > 0 && dp[m][n] == dp[m-1][n]) {
                m--;
            } else if(n > 0 && dp[m][n] == dp[m][n-1]) {
                n--;
            } else {
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }
    public static int[][] LCS(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1][len2];

        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for(int i = 1; i < len1; i++) {
            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : Math.max(dp[i-1][0], 0);
        }
        for(int j = 1; j < len2; j++) {
            dp[0][j] = text1.charAt(0) == text2.charAt(j) ? 1 : Math.max(dp[0][j-1], 0);
        }
        for(int i = 1; i < len1; i++) {
            for(int j = 1;  j < len2; j++) {
                if(text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp;
    }
}
