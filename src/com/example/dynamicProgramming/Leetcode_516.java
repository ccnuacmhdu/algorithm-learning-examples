package com.example.dynamicProgramming;

public class Leetcode_516 {
    /**
     * 最长回文子序列
     *
     * 转化成求最长公共子序列问题（原字符串和逆序后的字符串）
     * dp[i][j]，c1前i个字符和c2前j个字符最长公共子序列的长度
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        String s2 = (new StringBuffer(s)).reverse().toString();
        char[] c1 = s.toCharArray();
        char[] c2 = s2.toCharArray();
        int n = c1.length;
        int[][] dp = new int[n][n];
        int ans = dp[0][0] = (c1[0] == c2[0]) ? 1 : 0;
        for(int i = 1; i < n; i++) {
            dp[i][0] = (c1[i] == c2[0]) ? 1 : dp[i - 1][0];
            dp[0][i] = (c1[0] == c2[i]) ? 1 : dp[0][i - 1];
            ans = Math.max(Math.max(dp[i][0], dp[0][i]), ans);
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = (c1[i] == c2[j]) ? (dp[i - 1][j - 1] + 1) : Math.max(dp[i - 1][j], dp[i][j - 1]);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
