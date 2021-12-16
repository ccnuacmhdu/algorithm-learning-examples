package com.example.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_56 {
    /**
     * 贪心
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, (x, y) -> {
            if(x[1] != y[1]) {
                return y[1] - x[1];
            } else {
                return x[0] - y[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        list.add(0, intervals[0]);
        for(int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            int[] next = list.get(0);
            if(cur[1] < next[0]) {
                list.add(0, cur);
            } else {
                int st = Math.min(cur[0], next[0]);
                int en = Math.max(cur[1], next[1]);
                list.remove(0);
                list.add(0, new int[]{st, en});
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
