package com.example.binarySearch;

public class Leetcode_33 {
    /*
        观察规律，中间数小于右边，右边就有序，否则左边有序
        1 2 3 4 5
        2 3 4 5 1
        3 4 5 1 2
        4 5 1 2 3
        5 1 2 3 4
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int mid = l + ((r - l) >> 1);
            if(nums[mid] == target) return mid;
            if(nums[mid] <= nums[r]) {
                if(target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                if(target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }
}
