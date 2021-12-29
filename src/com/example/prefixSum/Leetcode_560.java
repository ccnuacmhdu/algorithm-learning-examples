package com.example.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_560 {

    /**
     * 前缀和
     *
     * prefix[i] = nums[0] + …… + nums[i]
     * [i,j]的和 = prefix[j] - prefix[i-1] = k 可等价为 prefix[j] = prefix[i-1] + k, 1 <= i <= j
     * 枚举所有 j，再查看过往出现 prefix[j] - k 的次数即可（注意 j == 0，单独处理，即 prefix[0] == k ? 即
     * 查看过往是否出现过 0，这个 0 得事先登记）
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int cnt = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            cnt += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }

    // 暴力法
//    public int subarraySum(int[] nums, int k) {
//        int cnt = 0;
//        for(int i = 0; i < nums.length; i++) {
//            int sum = 0;
//            for(int j = i; j < nums.length; j++) {
//                sum += nums[j];
//                if(sum == k) {
//                    cnt++;
//                }
//            }
//        }
//        return cnt;
//    }
}
