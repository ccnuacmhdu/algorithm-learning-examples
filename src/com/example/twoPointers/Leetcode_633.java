package com.example.twoPointers;

public class Leetcode_633 {
    // 双指针法
    public boolean judgeSquareSum(int c) {
        int min = 0, max = (int)(Math.sqrt(c));
        while(min <= max) {
            int check = min * min - (c - max * max);
            if(check == 0) {
                return true;
            } else if(check < 0) {
                min++;
            } else {
                max--;
            }
        }
        return false;
    }

    // 直接法
//    public boolean judgeSquareSum(int c) {
//        int min = 0, max = (int)(Math.sqrt(c));
//        for(int i = min; i <= max; i++) {
//            for(int j = i; j <= max; j++) {
//                if(i*i == c - j*j) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
