package com.example.math;

/**
 * 第k个排列
 */
public class Leetcode_60 {
    /**
     * 特别说明，参考资料：https://leetcode-cn.com/problems/permutation-sequence/solution/di-kge-pai-lie-shu-xue-gui-na-on2-by-acw_weian/
     * 从高位到低位依次确定每一位，简单数学分析，举例理解。以 n=4，k=16 为例，
     *
     * 分别以 1，2，3，4 打头的情况下，列举后边所有情况，每种情况对应数目是 3! = 6，那么 k = 16，k > 6*2 && k <= 6*3，
     * 必定在第 3 组，最终结果的第一个数也随之确定，即 3，更新 k = 16 - 6*2 = 4
     * 1（2，3，4 的全排列）
     * 2（1，3，4 的全排列）
     * 3（1，2，4 的全排列）
     * 4（1，2，3 的全排列）
     *
     * 确定了 3 打头后，列举后边所有情况，每种情况对应数目是 2! = 2，由于 k = 4，k > 2*1 && k <= 2*2，那么必定在第 2 组，
     * 那么最终结果的第二个数就是 2，更新 k = 4 - 2*2 = 0
     * 31（2，4 的全排列）
     * 32（1，4 的全排列）
     * 34（1，2 的全排列）
     *
     * 确定了前两位是 32 后，列举后边所有情况，每种情况对应数目是 1! = 1，由于 k = 0，k <= 1*1，那么必定在第 1 组，那么
     * 最终结果的第三个数就是 1，最终结果就是 3214
     * 3214
     * 3241
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation_02(int n, int k) {
        int[] factorial = new int[n + 1];
        // 求阶乘
        factorial[0] = 1;
        for(int i = 1; i <= n; i++) {
            factorial[i] = factorial[i-1] * i;
        }

        StringBuilder sb = new StringBuilder();
        boolean[] vis = new boolean[n+1];
        /**
         * 以 1，2，3，4 组成的全排列为例说明，i = [1, n] 就是分别枚举了前 i 位确定的情况下，
         * 后边分成 n-i 组，且每组有 (n-i)! 种排列。第 i 位可能是 [1,n]，但不能已经用过，
         * 依次确定第 i 位。
         */
        for(int i = 1;  i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(!vis[j]) {
                    if (k <= factorial[n - i]) {
                        sb.append(j);
                        vis[j] = true;
                        break;
                    }
                    k -= factorial[n - i];
                }
            }
        }
        return sb.toString();
    }

}
