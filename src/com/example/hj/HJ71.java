package com.example.hj;

import java.util.*;

/**
 * HJ71 字符串通配符
 */
public class HJ71 {
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
                        dp[i][j] = dp[i - 1][j - 1];
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
