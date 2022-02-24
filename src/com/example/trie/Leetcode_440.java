package com.example.trie;

public class Leetcode_440 {
    public int findKthNumber(long n, long k) {
        long prefix = 1;
        long idx = 1;
        while(idx < k) {
            long cnt = getCount(prefix, n);
            if(idx + cnt - 1 < k) {
                prefix++;
                idx += cnt;
            } else {
                prefix *= 10;
                idx++;
            }
        }
        return (int)prefix;
    }
    private long getCount(long prefix, long n) {
        long x = prefix;
        long y = prefix + 1;
        long cnt = 0;
        while(x <= n) {
            cnt += Math.min(y, n + 1) - x;
            x *= 10;
            y *= 10;
        }
        return cnt;
    }
}
