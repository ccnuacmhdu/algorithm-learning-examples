package com.example.twoPointers;

/**
 * 为啥可以用双指针？
 * 不妨假定 height[l] < height[r]，如果把 l 固定，不断往左移动 r，面积只会变小，
 * 所以，固定 l 并左移 r 没有必要了，应该固定 r 并右移 l（移动较小的）
 */
public class Leetcode_11 {
    public int maxArea(int[] height) {
        if(height == null || height.length < 2) return 0;
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            max = Math.max(max, area);
            if(height[l] < height[r]) {
                l++;
            } else if(height[l] > height[r]){
                r--;
            } else {
                l++;
                r--;
            }
        }
        return max;
    }
}
