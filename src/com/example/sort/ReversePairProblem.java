package com.example.sort;

import java.util.ArrayList;

/**
 * 【逆序对问题】
 * 在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，请打印所有逆序对。
 *
 * 【思路】
 * 暴力 / 归并排序
 */
public class ReversePairProblem {

    public static ArrayList<String> mergeSort(int[] a){
        if(a == null || a.length < 2){
            return new ArrayList<String>();
        }
        return mergeSort(a, 0, a.length-1);
    }

    public static ArrayList<String> mergeSort(int[] a, int left, int right){
        ArrayList<String> res = new ArrayList<String>();
        if(left == right){
            return res;
        }
        int mid = left + ((right - left) >> 1);
        ArrayList<String> list1 = mergeSort(a, left, mid);
        ArrayList<String> list2 = mergeSort(a, mid+1, right);
        ArrayList<String> list3 = merge(a, left, mid, right);
        for(int i = 0; i < list1.size(); i++){
            res.add(list1.get(i));
        }
        for(int i = 0; i < list2.size(); i++){
            res.add(list2.get(i));
        }
        for(int i = 0; i < list3.size(); i++){
            res.add(list3.get(i));
        }
        return res;
    }

    public static ArrayList<String> merge(int[] a, int left, int mid, int right){
        int[] help = new int[right-left+1];
        int index = right-left;
        int i = mid;
        int j = right;
        ArrayList<String> list = new ArrayList<String>();
        while(i >= left && j >= mid+1){
            if(a[i] > a[j]){
                for(int k = mid+1; k <= j; k++){
                    list.add("("+a[i]+","+a[k]+")");
                }
                help[index--] = a[i--];
            }else{
                help[index--] = a[j--];
            }
        }
        while(i >= left){
            help[index--] = a[i--];
        }
        while(j >= mid+1){
            help[index--] = a[j--];
        }
        for(int k = 0; k < help.length; k++){
            a[k+left] = help[k];
        }
        return list;
    }
}
