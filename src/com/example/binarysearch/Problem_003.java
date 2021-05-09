package com.example.binarysearch;

public class Problem_003 {
    /**
     * 如果花数大于等于 m*k，一定可以，否则，一定不可。最少的天数是 1，最大天数是最晚开花的天。
     *
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        if(bloomDay == null || bloomDay.length < m * k) {
            return -1;
        }
        int maxDay = 0;
        for(int i = 0; i < bloomDay.length; i++) {
            maxDay = Math.max(maxDay, bloomDay[i]);
        }
        int left = 1;
        int right = maxDay;
        int res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if(check(bloomDay, m, k, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
    private boolean check(int[] bloomDay, int m, int k, int days) {
        int flowers = 0;
        int bouquets = 0;
        for(int i = 0; i < bloomDay.length && bouquets < m; i++) {
            if(bloomDay[i] <= days) {
                flowers++;
                if(flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}
