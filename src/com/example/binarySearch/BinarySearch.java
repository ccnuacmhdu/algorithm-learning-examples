package com.example.binarySearch;

public class BinarySearch {
    public static int binarySearch(int[] a, int v) {
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
}