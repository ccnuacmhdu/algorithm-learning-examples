package com.example.bitManipulation;

public class Leetcode_260 {
    public int[] singleNumber(int[] nums) {
        int xorAll = 0;
        for(int i : nums) {
            xorAll ^= i;
        }
        int mostRight1Bit = xorAll & (~xorAll + 1);
        int res1 = 0;
        for(int i : nums) {
            if((i & mostRight1Bit) != 0) {
                res1 ^= i;
            }
        }
        int res2 = xorAll ^ res1;
        return new int[]{res1, res2};
    }
}
