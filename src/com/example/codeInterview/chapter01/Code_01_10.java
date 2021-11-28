package com.example.codeInterview.chapter01;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 最大值减去最小值小于或等于 num 的子数组数量
 *
 * 这道题的基础是题目“生成窗口最大值数组”，先搞明白这个套路，再分析下题目，结论如下，
 * 1）如果数组 arr[i..j]满足条件，即 max(arr[i..j])-min(arr[i..j])<=num，那么 arr[i..j]中
 * 的每一个子数组，即 arr[k..l] （i≤k≤l≤j）都满足条件。
 * 2）如果子数组 arr[i..j]不满足条件，那么所有包含 arr[i..j] 的数组，即 arr[k..l]（k≤i≤j≤l）
 * 都不满足条件。
 */
public class Code_01_10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int num = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int res = getCnt0(arr, num);
        System.out.println(res);
    }

    /**
     * 该代码易于理解
     *
     * @param arr
     * @param num
     * @return
     */
    public static int getCnt0(int[] arr, int num) {
        if(arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<>();  // 最大值递减队列
        LinkedList<Integer> qmin = new LinkedList<>();  // 最小值递增队列
        int left = 0;
        int right = 0;
        int res = 0;
        // 依次尝试以 arr[left] 开头
        while (left < arr.length) {
            while (right < arr.length) {
                if(qmin.isEmpty()) {
                    qmin.add(right);
                    qmax.add(right);
                    right++;
                } else {
                    while (!qmax.isEmpty() && arr[right] > arr[qmax.peekLast()]) {
                        qmax.pollLast();
                    }
                    if(qmax.isEmpty() || qmax.peekLast() != right) {    // 防止重复添加
                        qmax.add(right);
                    }
                    while (!qmin.isEmpty() && arr[right] < arr[qmin.peekLast()]) {
                        qmin.pollLast();
                    }
                    if(qmin.isEmpty() || qmin.peekLast() != right) {    // 防止重复添加
                        qmin.add(right);
                    }

                    // 移除过期的
                    if(qmax.peekFirst() < left) {
                        qmax.pollFirst();
                    }
                    if(qmin.peekFirst() < left) {
                        qmin.pollFirst();
                    }

                    // 子数组不满足，再扩大的数组肯定不满足
                    if(arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                        break;
                    }

                    right++;
                }
            }
            res += right - left;
            left++;
        }
        return res;
    }

    /**
     * 左神代码可以在理解上面代码基础上加以理解，可以把上面代码优化成左神的代码
     *
     * @param arr
     * @param num
     * @return
     */
    public static int getCnt(int[] arr, int num) {
        if(arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<>();  // 最大值递减队列
        LinkedList<Integer> qmin = new LinkedList<>();  // 最小值递增队列
        int left = 0;
        int right = 0;
        int res = 0;
        // 依次尝试以 arr[left] 开头
        while (left < arr.length) {
            while (right < arr.length) {
                if(qmin.isEmpty() || qmin.peekLast() != right) {
                    while (!qmax.isEmpty() && arr[right] >= arr[qmax.peekLast()]) {
                        qmax.pollLast();
                    }
                    qmax.add(right);
                    while (!qmin.isEmpty() && arr[right] <= arr[qmin.peekLast()]) {
                        qmin.pollLast();
                    }
                    qmin.add(right);
                }
                if(arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                right++;
            }
            res += right - left;
            if(qmax.peekFirst() == left) {
                qmax.pollFirst();
            }
            if(qmin.peekFirst() == left) {
                qmin.pollFirst();
            }
            left++;
        }
        return res;
    }
}
