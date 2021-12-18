package com.example.math;

public class Leetcode_96 {
    public int numTrees(int n) {
        int[][] dp = new int[n+1][n+1];
        return process(1, n, dp);
    }

    private int process(int st, int en, int[][] dp) {
        if(st >= en) return 1;
        if(dp[st][en] > 0) return dp[st][en];
        int ans = 0;
        for(int i = st; i <= en; i++) {
            int l = process(st, i - 1, dp);
            int r = process(i + 1, en, dp);
            ans += l * r;
        }
        return dp[st][en] = ans;
    }
}
