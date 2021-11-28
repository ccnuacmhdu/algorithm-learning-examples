package com.example.codeInterview.chapter01;

import java.util.Scanner;
import java.util.Stack;

/**
 * 求最大子矩阵的大小
 *
 * 1.对矩阵的每一行进行切割，统计以当前行为底的情况下，每个位置往上 1 的数量。
 *
 * 例如 map = 1 0 1 1
 *           1 1 1 1
 *           1 1 1 0
 *
 * 第 1 行，height = {1，0，1，1}
 * 第 2 行 height = {2，1，2，2}
 * 第 3 行 height = {3，2，3，0}
 *
 * 2.计算每一行为底的情况下，最大的矩形是什么。
 *
 * 实质是在一个直方图中求最大的矩形面积。如果能求出以每一根柱子扩展出去的最大矩形，
 * 那么最大的矩形就是要求的答案。像这种连续的取值，取值只受前后两端影响的情况，可
 * 以使用栈来实现（看“单调栈结构”那道题就知道了）。
 */
public class Code_01_09 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        int res = getMaxSubMatrixSize(map);
        System.out.println(res);
    }

    public static int getMaxSubMatrixSize(int[][] map) {
        if(map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxSize = 0;
        int[] height = new int[map[0].length];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                height[j] = (map[i][j] == 0) ? 0 : (height[j]+1);
            }
            maxSize = Math.max(maxSize, getMaxSubMatrixSizeFromBottom(height));
        }
        return maxSize;
    }

    private static int getMaxSubMatrixSizeFromBottom(int[] height) {
        if(height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < height.length; i++) {
            // 此处为啥取 >= 可行，试一下 height={2,2,2}的例子就相通了，更理解单调栈了
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int popIndex = stack.pop();
                int leftNearestLess = stack.isEmpty() ? -1 : stack.peek();
                int area = height[popIndex] * (i-1-leftNearestLess);
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        // 此时栈自底向上单调递增，必然能扩展到 height 最右边
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftNearestLess = stack.isEmpty() ? -1 : stack.peek();
            int area = height[popIndex] * (height.length - leftNearestLess - 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
