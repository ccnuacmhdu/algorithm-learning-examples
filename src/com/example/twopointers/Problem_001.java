package com.example.twopointers;

public class Problem_001 {
    // 双指针法
    public boolean judgeSquareSum(int c) {
//        long right = (long) Math.sqrt(c); // 优化
        long right = 1 << 16;
        long left = 0;
        while(left <= right) {
            long sum = left * left + right * right;
            if(sum == c) {
                return true;
            } else if(sum < c) {
                left++;
            } else{
                right--;
            }
        }
        return false;
    }

    // 直接法
//    public boolean judgeSquareSum(int c) {
//        long right = (long) Math.sqrt(c);
//        for(long i = 0; i <= right; i++) {
//            double j = Math.sqrt(c-i*i);
//            if(j == (long)j) {
//                return true;
//            }
//        }
//        return false;
//    }
}
