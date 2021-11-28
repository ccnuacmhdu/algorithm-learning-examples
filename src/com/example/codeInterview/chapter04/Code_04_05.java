package com.example.codeInterview.chapter04;

// [leetcode] 518. 零钱兑换 II
public class Code_04_05 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        int res = new Code_04_05().completeBackpack(coins, amount);
    }

    public int change(int amount, int[] coins) {
        // 暴力递归
//        return completeBackpack(coins, amount, coins.length - 1);

        // 记忆化搜索
//        int[][] dp = new int[coins.length][amount+1];
//        for(int i = 0; i < coins.length; i++) {
//            Arrays.fill(dp[i], -1);
//        }
//        return completeBackpack(coins, amount, coins.length - 1, dp);

        // 动态规划/动态规划+状态压缩/动态规划+公式压缩/动态规划+公式压缩+状态压缩
        return completeBackpack(coins, amount);
    }

    // 暴力递归（思路：完全背包）
    /*private int completeBackpack(int[] val, int target, int i) {
        if(i == -1) {
            return target == 0 ? 1 : 0;
        }
        if(target < 0) {
            return 0;
        }
        int ans = 0;
        for(int j = 0; j <= target / val[i]; j++) {
            ans += completeBackpack(val, target - j * val[i], i - 1);
        }
        return ans;
    }*/

    // 记忆化搜索
    /*private int completeBackpack(int[] val, int target, int i, int[][] dp) {
        if(i == -1) {
            return target == 0 ? 1 : 0;
        }
        if(target < 0) {
            return 0;
        }
        if(dp[i][target] != -1) {
            return dp[i][target];
        }
        int ans = 0;
        for(int j = 0; j <= target / val[i]; j++) {
            ans += completeBackpack(val, target - j * val[i], i - 1, dp);
        }
        return dp[i][target] = ans;
    }*/

    // 动态规划
    /*private int completeBackpack(int[] val, int target) {
        if(val == null || val.length == 0) {
            return target == 0 ? 1 : 0;
        }
        int[][] dp = new int[val.length][target+1];
        for(int i = 0; i <= target; i++) {
            dp[0][i] = i % val[0] == 0 ? 1 : 0;
        }
        for(int i = 1; i < val.length; i++) {
            dp[i][0] = 1;
            for(int j = 1; j <= target; j++) {
                int ans = 0;
                for(int k = 0; k <= j / val[i]; k++) {
                    ans += dp[i-1][j-k*val[i]];
                }
                dp[i][j] = ans;
            }
        }
        return dp[val.length-1][target];
    }*/

    // 动态规划+状态压缩
//    private int completeBackpack(int[] val, int target) {
//        if(val == null || val.length == 0) {
//            return target == 0 ? 1 : 0;
//        }
//        int[] dp = new int[target+1];
//        int[] tmp = Arrays.copyOf(dp, dp.length);
//        for(int i = 0; i <= target; i++) {
//            tmp[i] = dp[i] = i % val[0] == 0 ? 1 : 0;
//        }
//        for(int i = 1; i < val.length; i++) {
//            for(int j = 1; j <= target; j++) {
//                int ans = 0;
//                for(int k = 0; k <= j / val[i]; k++) {
//                    ans += tmp[j-k*val[i]];
//                }
//                dp[j] = ans;
//            }
//            tmp = Arrays.copyOf(dp, dp.length);
//        }
//        return dp[target];
//    }



    // 动态规划+公式压缩
//    dp[i][j] = dp[i-1][j-0*v] + dp[i-1][j-1*v] + …… + dp[i-1][j-k*v]  k:[0, j/v]
//    dp[i][j-v] = dp[i-1][(j-v)-0*v] + dp[i-1][(j-v)-1*v] + …… + dp[i-1][(j-v)-k*v] k:[0, (j-v)/v]
//    dp[i][j] = dp[i-1][j] + dp[i][j-v]
    /*private int completeBackpack(int[] val, int target) {
        if(val == null || val.length == 0) {
            return target == 0 ? 1 : 0;
        }
        int[][] dp = new int[val.length][target+1];
        for(int i = 0; i <= target; i++) {
            dp[0][i] = i % val[0] == 0 ? 1 : 0;
        }
        for(int i = 1; i < val.length; i++) {
            dp[i][0] = 1;
            for(int j = 1; j <= target; j++) {
                dp[i][j] = dp[i-1][j] + (j >= val[i] ? dp[i][j-val[i]] : 0);
            }
        }
        return dp[val.length-1][target];
    }*/

    // 动态规划+公式压缩+状态压缩
    private int completeBackpack(int[] val, int target) {
        if(val == null || val.length == 0) {
            return target == 0 ? 1 : 0;
        }
        int[] dp = new int[target+1];
        for(int i = 0; i <= target/val[0]; i++) {
            dp[i*val[0]] = 1;
        }
        for(int i = 1; i < val.length; i++) {
            for(int j = 1; j <= target; j++) {
                dp[j] += (j >= val[i] ? dp[j-val[i]] : 0);
            }
        }
        return dp[target];
    }
}
