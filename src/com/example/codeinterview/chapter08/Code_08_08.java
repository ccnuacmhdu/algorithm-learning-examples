package com.example.codeinterview.chapter08;

public class Code_08_08 {
    public static int binarySearch(int[] a){
        if(a == null || a.length == 0){
            return -1;
        }
        if(a.length == 1){
            return 0;
        }
        if(a[0] < a[1]) {
            return 0;
        }
        if(a[a.length - 1] < a[a.length - 2]){
            return a.length-1;
        }
        int left = 0;
        int right = a.length-1;
        while(left <= right){
            int mid = left +( (right - left) >> 1);
            if(a[mid] > a[mid+1]){
                left = mid + 1;
            }else if(a[mid] > a[mid-1]){
                right = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }

}
