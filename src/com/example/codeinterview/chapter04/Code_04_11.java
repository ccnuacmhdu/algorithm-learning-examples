package com.example.codeinterview.chapter04;

// [leetcode] 剑指 Offer 46. 把数字翻译成字符串
public class Code_04_11 {
    public int translateNum(int num) {
        char[] chs = String.valueOf(num).toCharArray();
        return process(chs, 0, chs.length - 1);
    }
    // 递归1法
    public int process(char[] chs, int i, int j) {
        if(i >= j) {
            return 1;
        }
        int res1, res2 = 0;
        res1 = process(chs, i+1, j);
        int sum = (chs[i] - '0') * 10 + (chs[i+1] - '0');
        if(sum < 26 && sum > 9) {
            res2 = process(chs, i + 2, j);
        }
        return res1 + res2;
    }
    // 递归2法
    public int process2(char[] chs, int i) {
        if(i >= chs.length - 1) {
            return 1;
        }
        int res1, res2 = 0;
        res1 = process2(chs, i+1);
        int sum = (chs[i] - '0') * 10 + (chs[i+1] - '0');
        if(sum < 26 && sum > 9) {
            res2 = process2(chs, i + 2);
        }
        return res1 + res2;
    }
    // 动态规划
    public int translateNum2(int num) {
        char[] chs = String.valueOf(num).toCharArray();
        int len = chs.length;
        int[] dp = new int[len+1];
        dp[len] = dp[len-1] = 1;
        for(int i = len -2; i >= 0; i--) {
            int sum = (chs[i] - '0') * 10 + (chs[i+1] - '0');
            if(sum < 26 && sum > 9) {
                dp[i] = dp[i+2] + dp[i+1];
            } else {
                dp[i] = dp[i+1];
            }
        }
        return dp[0];
    }

}
