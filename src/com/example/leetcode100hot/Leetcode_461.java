package com.example.leetcode100hot;

public class Leetcode_461 {
//    public int hammingDistance(int x, int y) {
//        return Integer.bitCount(x ^ y);
//    }
//    public int hammingDistance(int x, int y) {
//        int n = x ^ y;
//        int cnt = 0;
//        for(int i = 0; i < 32; i++) {
//            if(((1 << i) & n) != 0) {
//                cnt++;
//            }
//        }
//        return cnt;
//    }
    public int hammingDistance(int x, int y) {
        int n = x ^ y;
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n &= (n-1);
        }
        return cnt;
    }
}
