package com.example.codeinterview.chapter03;

import java.util.Scanner;

public class Code_03_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        boolean res = isPostArray(arr);
        System.out.println(res);
    }

    public static boolean isPostArray(int[] arr) {
        if(arr == null || arr.length == 0) {
            return false;
        }
        return isPostArray(arr, 0, arr.length - 1);
    }
    private static boolean isPostArray(int[] arr, int st, int en) {
        if(st == en) {
            return true;
        }
        int less = -1;
        int bigger = -1;
        // 找到大于arr[en]的第一个位置，找到小于arr[en]的最后一个位置
        for(int i = st; i < en; i++) {
            if(arr[i] < arr[en]) {
                less = i;
            } else {
                bigger = bigger == -1 ? i : bigger;
            }
        }

        if(bigger == -1 || less == -1) {
            return isPostArray(arr, st, en - 1);
        }
        if(less > bigger) {
            return false;
        }
        return isPostArray(arr, st, less) && isPostArray(arr, bigger, en - 1);
    }
}
