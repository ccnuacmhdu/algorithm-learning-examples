package com.example.codeinterview.chapter01;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 生成窗口最大值数组
 */
public class Code_01_07 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int windowSize = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] res = getWindowMaxArr(arr, windowSize);
        for(int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    /**
     * 维护一个动态最大值降序队列，存放各个值的坐标。队头就是当前最大值，队头失效要出队。
     *
     * O(N)，N 个数进队出队加在一起最多 2*N 次操作，整体还是 O(N)
     *
     * @param arr
     * @param windowSize
     * @return
     */
    public static int[] getWindowMaxArr(int [] arr, int windowSize) {
        if(arr == null || windowSize < 1 || arr.length < windowSize) {
            return null;
        }
        // 窗口数量
        int cnt = arr.length - windowSize + 1;
        int[] res = new int[cnt];
        int index = 0;

        LinkedList<Integer> maxQue = new LinkedList<>();

        for(int i = 0; i < arr.length; i++) {
            while (!maxQue.isEmpty() && arr[i] > arr[maxQue.peekLast()]) {
                maxQue.pollLast();
            }
            // 队列为空或 arr[i] > arr[maxQue.peekLast()]
            maxQue.addLast(i);

            // 过期
            if(maxQue.peekFirst() <= i - windowSize) {
                maxQue.pollFirst();
            }

            // 达到第一个窗口大小才开始取
            if(i >= windowSize - 1) {
                res[index++] = arr[maxQue.peekFirst()];
            }
        }

        return res;
    }

    /**
     * 暴力法（超时，O(N*W)）
     *
     * @param arr
     * @param windowSize
     * @return
     */
//    public static int[] getWindowMaxArr(int [] arr, int windowSize) {
//        if(arr == null) {
//            return new int[]{};
//        }
//        // 窗口数量
//        int cnt = arr.length - windowSize + 1;
//        if(cnt <= 0) {
//            return new int[]{};
//        }
//        int[] res = new int[cnt];
//        for(int i = 0; i < cnt; i++) {
//            int max = Integer.MIN_VALUE;
//            for(int j = i; j < i + windowSize; j++) {
//                max = Math.max(max, arr[j]);
//            }
//            res[i] = max;
//        }
//        return res;
//    }
}
