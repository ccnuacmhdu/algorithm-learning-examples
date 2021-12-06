package com.example.dynamicProgramming;

public class Leetcode_5 {
    // dp[i][j] 表示 s[i]~s[j] 是回文串，if(s[i]=s[j] && (i+1==j || dp[i+1][j-1])) dp[i][j] = true
    // 注意前提条件 i+1 <= j-1 && i <= j，填表得搞定左下方，其实就是搞定 i+1 = j 的情况
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2) {
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean [n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int max = 1;
        int l = 0, r = 0;
        for(int i = n - 2; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                char c1 = s.charAt(i);
                char c2 = s.charAt(j);
                if(c1 != c2) {
                    dp[i][j] = false;
                } else {
                    dp[i][j] = (i + 1 == j || dp[i+1][j-1]);
                }
                if(dp[i][j] && max < (j - i + 1)) {
                    max = j - i + 1;
                    l = i;
                    r = j;
                }
            }
        }
        return s.substring(l, r+1);
    }
}
