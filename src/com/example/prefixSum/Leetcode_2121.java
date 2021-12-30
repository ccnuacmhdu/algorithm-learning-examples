package com.example.prefixSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode_2121 {
    public long[] getDistances(int[] arr) {
        int n = arr.length;
        long[] ans = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<Integer>());
            list.add(i);
            map.put(arr[i], list);
        }
        for(List<Integer> list : map.values()) {
            int size = list.size();
            // 注意使用 long，用 Integer 会越界
            // l[i] 表示左边元素对 i 号相等元素的间隔和
            // r[i] 表示右边元素对 i 号相等元素的间隔和
            // l[i] + r[i] 就是其他所有相等元素对 i 的间隔和
            long[] l = new long[size];
            long[] r = new long[size];
            for(int i = 1; i < size; i++) {
                l[i] = l[i - 1] + (list.get(i) - list.get(i - 1)) * i;
            }
            for(int j = size - 2; j >= 0; j--) {
                r[j] = r[j + 1] + (list.get(j + 1) - list.get(j)) * (size - 1 - j);
            }
            for(int i = 0; i < size; i++) {
                ans[list.get(i)] = l[i] + r[i];
            }
        }
        return ans;
    }
}
