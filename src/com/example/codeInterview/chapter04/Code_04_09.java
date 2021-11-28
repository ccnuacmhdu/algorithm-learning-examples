package com.example.codeInterview.chapter04;

import java.util.Scanner;

public class Code_04_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();
        String res = getLCS(s1, s2);
        System.out.println(res);
    }

    public static String getLCS(String s1, String s2) {
        if(s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return "-1";
        }
        int[][] dp = LCS(s1, s2);
        int max = 0;
        int posX = 0;
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(max < dp[i][j]) {
                    posX = i;
                    max = dp[i][j];
                }
            }
        }
        if(max == 0) {
            return "-1";
        }
        return s1.substring(posX-max+1, posX+1);
    }
    public static int[][] LCS(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1][len2];

        for(int i = 0; i < len1; i++) {
            dp[i][0] = s1.charAt(i) == s2.charAt(0) ? 1 : 0;
        }
        for(int j = 0; j < len2; j++) {
            dp[0][j] = s1.charAt(0) == s2.charAt(j) ? 1 : 0;
        }
        for(int i = 1; i < len1; i++) {
            for(int j = 1; j < len2; j++) {
                dp[i][j] = s1.charAt(i) == s2.charAt(j) ? dp[i-1][j-1] + 1 : 0;
            }
        }
        return dp;
    }
}
