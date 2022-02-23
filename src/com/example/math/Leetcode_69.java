package com.example.math;

/**
 * 平方根
 */
public class Leetcode_69 {
    public int mySqrt(int x) {
        long l = 0, r = x, xx = x;
        long ans = 0;
        while(l <= r) {
            long mid = l + ((r - l) >> 1);
            if(mid * mid <= xx) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int)ans;
    }
}
