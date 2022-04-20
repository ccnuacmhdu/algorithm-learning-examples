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


    /**
     * 正常二分思路，二分方式找到两段有序的再分别二分
     *
     * @param nums
     * @param target
     * @return
     */
//    public int search(int[] nums, int target) {
//        int l1 = 0;
//        int r1 = biBigR(nums, nums[0]);
//        int l2 = r1 + 1;
//        int r2 = nums.length - 1;
//        int ret1 = bi(nums, target, l1, r1);
//        if(ret1 != -1) {
//            return ret1;
//        }
//        int ret2 = bi(nums, target, l2, r2);
//        if(ret2 != -1) {
//            return ret2;
//        }
//        return -1;
//    }
//    private int bi(int[] a, int v, int l, int r) {
//        int loc = -1;
//        while(l <= r) {
//            int mid = l + ((r - l) >> 1);
//            if(a[mid] == v) {
//                return mid;
//            } else if(a[mid] > v){
//                r = mid - 1;
//            } else {
//                l = mid + 1;
//            }
//        }
//        return loc;
//    }
//    private int biBigR(int[] a, int v) {
//        int l = 0, r = a.length - 1;
//        int loc = 0;
//        while(l <= r) {
//            int mid = l + ((r - l) >> 1);
//            if(a[mid] >= v) {
//                loc = mid;
//                l = mid + 1;
//            } else {
//                r = mid - 1;
//            }
//        }
//        return loc;
//    }

}
