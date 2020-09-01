package com.example.binarySearch;

/**
 * 查找有序数组（以升序为例）中大于等于某数的最小下标
 */
public class BinarySearchExample01 {
    public static int binarySearchLeftMost(int[] a, int v){
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
