package com.example.leetcodeTmp;

import java.util.Arrays;

/**
 * 【所有排列中的最大和】
 */
public class Leetcode_5505 {
    /**
     * 【方法一：超时】
     * 总的方法容易想，就是统计每个数出现次数，按照出现次数多少排序，出现次数高的就给从 nums 数组中选个大数。
     * 本题数据规模来看，在统计每个数出现次数的时候使用了差分数组辅助，否则超时。。。
     *
     * @param nums
     * @param requests
     * @return
     */
    public int maxSumRangeQuery_00(int[] nums, int[][] requests) {
        int mod = (int)(1e9 + 7);
        Arrays.sort(nums);
        int[] map = new int[nums.length];
        for(int i = 0; i < requests.length; i++) {
            // 这样做超时，看下数据规模就明白了。。竟然使用了奇技淫巧——辅助数组
            for(int j = requests[i][0]; j <= requests[i][1]; j++) {
                map[j]++;
            }
        }
        Arrays.sort(map);
        long res = 0;
        int cnt = nums.length - 1;
        for(int i = map.length - 1; i >= 0 && map[i] != 0; i--) {
            res = ((res % mod) +  (map[i]*nums[cnt--]) % mod) % mod;
        }
        return (int)(res % mod);
    }

    /**
     * 【方法二：改进方法一（使用差分数组辅助）】
     *
     * 差分数组举例子，数组 nums = [0, 2, 5, 4, 9]，d[i] = nums[i] - nums[i-1]
     * i        0   1   2   3   4
     * nums[i]  0   2   5   4   9
     * d[i]         2   3   -1  5
     *
     * 如果对 nums 数组 [1, 3] 区间同时加 3，不用逐个枚举加 1，只需要 d[1]+3 并且 d[4]-3
     * d[i]         5   3   -1  2
     * 最后算 nums 的时候，nums[i] = nums[i-1] + d[i] 可以得到，
     * nums[i] 0    5   8   7   9
     *
     * 类似的，如果对 nums 数组 [2, 3] 区间同时减 2，只需要 d[2]-2 并且 d[4]+2，并且多次对 nums 区间操作重叠可在 d 直接累计
     * 辅助数组 d 的存在，使得最后计算 nums 时最多对 nums 遍历一遍即可，适用于对区间同时加减相同的数的情形
     *
     * @param nums
     * @param requests
     * @return
     */
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int mod = (int)(1e9 + 7);
        Arrays.sort(nums);
        int[] help = new int[nums.length+1];
        int[] count = new int[nums.length+1];
        for(int i = 0; i < requests.length; i++) {
            help[requests[i][0]] += 1;
            help[requests[i][1]+1] -= 1;
        }
        count[0] = help[0] + count[0];
        for(int i = 1; i < nums.length; i++) {
            count[i] = help[i] + count[i-1];
        }
        Arrays.sort(count);
        long res = 0;
        int cnt = nums.length - 1;
        for(int i = count.length - 1; i >= 0 && count[i] != 0; i--) {
            res = ((res % mod) +  (count[i]*nums[cnt--]) % mod) % mod;
        }
        return (int)(res % mod);
    }

    public static void main(String[] args) {
        Leetcode_5505 leetcode_5505 = new Leetcode_5505();
        int[] nums = {1,2,3,4,5};
        int[][] requests = {{1,3},
                            {0,1}};
        int res = leetcode_5505.maxSumRangeQuery(nums, requests);
        System.out.println(res);
    }
}
