package com.example.leetcode;

/**
 * 【秋叶收藏集】
 */
public class Leetcode_LCP_19 {
    /**
     * 【方法一：动态规划】
     * 状态定义（玄学，本题目有用的就这三个状态）：
     * 1. dp[0][i] 表示 [0, i] 为全红状态最小修改次数
     * 2. dp[1][i] 表示 [0, i] 为红黄状态最小修改次数
     * 3. dp[2][i] 表示 [0, i] 为红黄红状态最小修改次数
     *
     * 状态转移：
     * dp[0][i] = dp[0][i-1] + (leaves.charAt(i) != 'r' ? 1 : 0);
     * dp[1][i] = Math.min(dp[0][i-1], dp[1][i-1]) + (leaves.charAt(i) != 'y' ? 1 : 0);
     * dp[2][i] = Math.min(dp[1][i - 1], dp[2][i - 1]) + (leaves.charAt(i) != 'r' ? 1 : 0);
     *
     * 边界：
     * dp[0][0] = leaves.charAt(0) != 'r' ? 1 : 0;
     * // 注意下面要加上括号，好坑啊。。
     * dp[0][1] = dp[0][0] + (leaves.charAt(1) != 'r' ? 1 : 0);
     * dp[1][0] = dp[0][0];
     * dp[1][1] = Math.min(dp[1][0], dp[0][0]) + (leaves.charAt(1) != 'y' ? 1 : 0);
     * dp[2][0] = dp[1][0];
     * dp[2][1] = dp[1][1];
     *
     * @param leaves
     * @return
     */
    public int minimumOperations(String leaves) {
        int[][] dp = new int[3][leaves.length()];
        dp[0][0] = leaves.charAt(0) != 'r' ? 1 : 0;
        // 注意下面要加上括号，好坑啊。。
        dp[0][1] = dp[0][0] + (leaves.charAt(1) != 'r' ? 1 : 0);
        dp[1][0] = dp[0][0];
        dp[1][1] = Math.min(dp[1][0], dp[0][0]) + (leaves.charAt(1) != 'y' ? 1 : 0);
        dp[2][0] = dp[1][0];
        dp[2][1] = dp[1][1];

        for(int i = 2; i < leaves.length(); i++) {
            // 注意下面要加上括号，好坑啊。。
            dp[0][i] = dp[0][i-1] + (leaves.charAt(i) != 'r' ? 1 : 0);
            dp[1][i] = Math.min(dp[0][i-1], dp[1][i-1]) + (leaves.charAt(i) != 'y' ? 1 : 0);
            dp[2][i] = Math.min(dp[1][i - 1], dp[2][i - 1]) + (leaves.charAt(i) != 'r' ? 1 : 0);
        }
        return dp[2][leaves.length()-1];
    }

    /**
     * 【方法二：前缀数组】
     * 最终形成的必然是红黄红状态，可以假定 [0, i) 和 [j, n) 为红, [i, j) 为黄，1 <= i < j < n
     * sum[x] 表示 [0, x) 中为红的数目，那么到最终状态要修改的次数为 (i - sum[i]) +
     * (sum[j]-sum[i]) + (n-j - (sum[n]-sum[j])) = n-sum[n] + i-2*sum[i]-(j-2*sum[j])
     *
     * 那么，在 j 固定的情况下，就是求 i-2*sum[i] 的最小值，就可以枚举 j，求出对应的 i-2*sum[i] 的最小值即可，
     *
     * @param leaves
     * @return
     */
    public int minimumOperations_02(String leaves) {
        int n = leaves.length();
        int[] sum = new int[n+1];
        sum[1] = leaves.charAt(0) == 'r' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            sum[i] = sum[i-1] + (leaves.charAt(i-1) == 'r' ? 1 : 0);
        }
        int[] min = new int[n-1];
        min[1] = 1 - 2*sum[1];
        for(int i = 2; i <= n-2; i++) {
            min[i] = Math.min(min[i-1], i - 2*sum[i]);
        }
        int res = Integer.MAX_VALUE;
        for(int j = 2; j < n; j++) {
            res= Math.min(n-sum[n] + min[j-1]-(j-2*sum[j]), res);
        }
        return res;
    }

    public static void main(String[] args) {
        Leetcode_LCP_19 leetcode_lcp_19 = new Leetcode_LCP_19();
        String leaves = "ryr";
        System.out.println(leetcode_lcp_19.minimumOperations_02(leaves));
    }
}
