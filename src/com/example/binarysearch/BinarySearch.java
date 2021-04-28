package com.example.binarysearch;

public class BinarySearch {
    public int binarySearch(int[] a, int v) {
        if (null == a || a.length == 0) {
            return -1;
        }
        int l = 0;
        int r = a.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (a[mid] == v) {
                return mid;
            } else if (a[mid] < v) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    // 查找有序数组（以升序为例）中大于等于某数的最小下标
    public int binarySearchLeftMost(int[] a, int v){
        if(a == null || a.length == 0){
            return -1;
        }
        int left = 0;
        int right = a.length-1;
        int index = -1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if(a[mid] >= v){
                index = mid;
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }
        return index;
    }
}