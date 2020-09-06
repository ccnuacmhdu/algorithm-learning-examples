package com.example.leetcode;

import com.example.skipList.SkipList;

import java.util.*;

/**
 * 【第k个排列】
 *
 */
public class Leetcode_60 {
    /**
     * 【方式一：回溯（超时）】
     * 回溯法求全排列，然后排序，然后输出第 k 个
     * @param n
     * @param k
     * @return
     */
    public String getPermutation_01(int n, int k) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(i);
        }
        List<String> res = permutation(sb.toString());
        // 排序
        Collections.sort(res);
        return res.get(k-1);
    }

    public ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process(chs, 0, res);
        return res;
    }
    // 每个字符依次打头。
    // 可能顺序乱了
    public void process(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        // 1-9 共 9 个数
        boolean[] visit = new boolean[10];
        for (int j = i; j < str.length; j++) {
            if (!visit[str[j]-'0']) {
                visit[str[j]-'0'] = true;
                swap(str, i, j);
                process(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }

    public void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    /**
     * 【方式二：数学】
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
         * 后边分成 n-i+1 组，且每组有 (n-i)! 种排列。第 i 位可能是 [1,n]，但不能已经用过，
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

    /**
     * 【方式三：基于方式二的改版】
     * 外表看起来好像是 O(N) 的时间复杂度，事实上耗时更多了！因为在 for 循环中使用了 List 数据结构，
     * List 做查操作仍需要 O(N)，最终时间复杂度依然是 O(N^2)。实际耗时比方式二还要多，因为使用越多
     * 的类库，就更耗时。
     * @param n
     * @param k
     * @return
     */
    public String getPermutation_03(int n, int k) {
        List<Integer> digits = new ArrayList<>();
        int[] factorial = new int[n + 1];
        // 求阶乘
        factorial[0] = 1;
        for(int i = 1; i <= n; i++) {
            factorial[i] = factorial[i-1] * i;
            digits.add(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1;  i <= n; i++){
            if (k <= factorial[n - i]) {
                sb.append(digits.remove(0));
            } else {
                int quotient = k / factorial[n-i];
                int remainder = k % factorial[n-i];
                int index = remainder == 0 ? quotient - 1 : quotient;
                if(remainder == 0) {
                    k -= (quotient-1)*factorial[n-i];
                } else {
                    k -= quotient * factorial[n-i];
                }
                sb.append(digits.remove(index));
            }
        }
        return sb.toString();
    }

    /**
     * 【方式四：行不通】
     * 发现优化点，和人讨论后，说是可以用二叉平衡搜索树/跳表等结构把有序数序列查询/删除复杂度降低到 O(logN)，
     * 但实操后发现行不通，因为参看方式三可知删除某节点会导致一些元素的索引发生改变，而跳表不能像 List 一样动态
     * 改变各个元素的索引，根本行不通。。。
     */

    // test
    public static void main(String[] args) {
        Leetcode_60 leetcode_60 = new Leetcode_60();
        for(int i = 1; i <= 24; i++) {
            System.out.println(leetcode_60.getPermutation_03(4, i));
        }
    }
}
