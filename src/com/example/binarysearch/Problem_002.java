package com.example.binarysearch;

import java.util.Arrays;

public class Problem_002 {
    /**
     * 思路：船运载能力最低得能装的下最重的货物，而最小的上限就是能装下所有货物。
     *      然后，二分找到运送天数小于等于 D 的最小值就 OK 了，按顺序取，那就
     *      每次贪心地尽可能多的取，看看最小需要多少天。
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int right = 0;
        int left = 0;
        for(int i = 0; i < weights.length; i++) {
            right += weights[i];
            left = Math.max(left, weights[i]);
        }
        int res = right;
        int mid = 0;
        int days = 0;
        int sum = 0;
        while (left <= right) {
            mid = left + ((right -left) >> 1);
            days = 1;
            sum = 0;
            for(int i = 0; i < weights.length; i++) {
                if(sum + weights[i] <= mid) {
                    sum += weights[i];
                } else {
                    sum = weights[i];
                    days++;
                }
                if(days > D) {
                    break;
                }
            }
            if(days <= D) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
