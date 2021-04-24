package com.example.codeinterview.chapter08;

import java.util.HashMap;

/**
 * 给定一个无序数组 arr，其中元素可正、可负、可 0。给定一个整数 k，求 arr 所有的子数组
 * 中累加和为 k 的最长子数组长度。
 * 补充问题 1：给定一个无序数组 arr，其中元素可正、可负、可 0。求 arr 所有的子数组中
 * 正数与负数个数相等的最长子数组长度。
 * 补充问题 2：给定一个无序数组 arr，其中元素只是 1 或 0。求 arr 所有的子数组中 0 和 1
 * 个数相等的最长子数组长度。
 */
public class Code_08_06 {
    // 注意题目中说的子数组是连续的！
    public int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 记录累加和第一次是某值的位置，比如，数组[1,2,3,3]，k=6。如果从 0 位置开始累加，也就是遍历之前不加入(0,-1)记录，
        // 当遍历到第一个 3 时，sum=6，此时 sum-k=6-6=0，所以在 map 中查询累加和 0 最早出现的位置，发现没有出现过。那么
        // 子数组[1,2,3]就被我们忽略。
        map.put(0, -1); // 重要
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }
//    第一个补充问题是先把数组 arr 中 的正数全部变成 1，负数全部变成-1，0 不变，然后求累加和为 0 的最长子数组长度即可。
//    第二个补充问题是先把数组 arr 中的 0 全部变成-1，1 不变，然后求累加和为 0 的最长子数组长度即可。
}
