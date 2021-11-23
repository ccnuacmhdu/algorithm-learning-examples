package com.example.leetcode100hot;

public class Leetcode_5 {
    // dp[i][j] 表示 s[i]~s[j] 是回文串，if(s[i]=s[j] && (i+1==j || dp[i+1][j-1])) dp[i][j] = true
    // 注意前提提交 i+1 <= j-1 && i <= j，填表
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        int max = 1, st = 0, en = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int i = n-1; i >= 0; i--) {
            dp[i][i] = true;
            for(int j = i+1; j < n; j++) {
                if(s.charAt(i) == s.charAt(j) && (i+1 == j || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    if(max < j-i+1) {
                        max = j-i+1;
                        st = i;
                        en = j;
                    }
                }
            }
        }
        return s.substring(st, en+1);
    }
}
