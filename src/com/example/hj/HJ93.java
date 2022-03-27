package com.example.hj;

import java.util.*;

/**
 * HJ93 数组分组
 */
public class HJ93 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        int sum3 = 0;
        int sum5 = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            sum += arr[i];
            if(arr[i] % 3 == 0 && arr[i] % 5 != 0) {
                sum3 += arr[i];
            } else if(arr[i] % 3 != 0 && arr[i] % 5 == 0) {
                sum5 += arr[i];
            } else {
                list.add(arr[i]);
            }
        }
        if(sum % 2 != 0) {
            System.out.println(false);
        } else {
            int halfSum = sum / 2;
            boolean check = f(list, halfSum - sum3, 0);
            System.out.println(check);
        }
    }

    // 01背包思想
    private static boolean f(List<Integer> list, int v, int i) {
        if(i == list.size()) return v == 0;
        return f(list, v, i + 1) || f(list, v - list.get(i), i + 1);
    }
}
