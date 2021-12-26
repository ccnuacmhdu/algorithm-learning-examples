package com.example.bitManipulation;

public class Leetcode_338 {

    // 动态规划
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }

//    public int[] countBits(int n) {
//        int[] bits = new int[n + 1];
//        for(int i = 0; i <= n; i++) {
//            // bits[i] = Integer.bitCount(i);
//            bits[i] = getBits(i);
//        }
//        return bits;
//    }
//
//    public int getBits(int n) {
//        int bits = 0;
//        while(n != 0) {
//            bits++;
//            n &= (n - 1);
//        }
//        return bits;
//    }

}
