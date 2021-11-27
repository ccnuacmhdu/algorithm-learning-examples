package com.example.binarySearch;

public class Leetcode_1482 {
    /**
     * 如果花数大于等于 m*k，一定可以，否则，一定不可。最少的天数是 1，最大天数是最晚开花的天。
     *
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if(n < m * k) return -1;
        int maxDays = 1;
        for(int i = 0; i < n; i++) {
            maxDays = Math.max(maxDays, bloomDay[i]);
        }
        int l = 1, r = maxDays;
        while(l <= r) {
            int mid = l + ((r - l) >> 1);
            if(check(bloomDay, mid, m, k)) {
                r = mid - 1;
                maxDays = mid;
            } else {
                l = mid + 1;
            }
        }
        return maxDays;
    }
    private boolean check(int[] bloomDay, int days, int m, int k) {
        int flowers = 0;
        for(int i = 0; i < bloomDay.length; i++) {
            if(bloomDay[i] <= days) {
                flowers++;
                if(flowers == k) {
                    m--;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return m <= 0;
    }
}
