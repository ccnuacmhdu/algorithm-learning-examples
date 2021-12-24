package com.example.dynamicProgramming;

public class Leetcode_221 {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 动态规划，以 matrix[i][j] 为右下角最大正方形边长 dp(i, j) = min(dp(i - 1, j), dp(i, j - 1), dp(i - 1, j - 1)) + 1, matrix[i][j] = '1'
        int[][] dp = new int[rows][cols];
        int maxEdge = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i * j == 0) {
                    dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    dp[i][j] = matrix[i][j] == '0' ? 0 : Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
                maxEdge = Math.max(maxEdge, dp[i][j]);
            }
        }
        return maxEdge * maxEdge;
    }

    // 求以每个点为左上角所延展的最大正方形
//    public int maximalSquare(char[][] matrix) {
//        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
//            return 0;
//        }
//        int rows = matrix.length;
//        int cols = matrix[0].length;
//        int[][] rMax = new int[rows][cols];
//        int[][] dMax = new int[rows][cols];
//        for(int i = 0; i < rows; i++) {
//            rMax[i][cols - 1] = matrix[i][cols - 1] == '0' ? 0 : 1;
//            for(int j = cols - 2; j >= 0; j--) {
//                rMax[i][j] = matrix[i][j] == '0' ? 0 : rMax[i][j + 1] + 1;
//            }
//        }
//
//        for(int j = 0; j < cols; j++) {
//            dMax[rows - 1][j] = matrix[rows - 1][j] == '0' ? 0 : 1;
//            for(int i = rows - 2; i >= 0; i--) {
//                dMax[i][j] = matrix[i][j] == '0' ? 0 : dMax[i+1][j] + 1;
//            }
//        }
//
//        int maxEdge = 0;
//        for(int i = 0; i < rows; i++) {
//            for(int j = 0; j < cols; j++) {
//                int maxEdgeTmp = Math.min(rMax[i][j], dMax[i][j]);
//                for(int p = i + 1, q = j + 1; p < i + maxEdgeTmp && q < j + maxEdgeTmp; p++, q++) {
//                    int steps = p - i;
//                    maxEdgeTmp = Math.min(Math.min(rMax[p][q], maxEdgeTmp - steps), Math.min(dMax[p][q], maxEdgeTmp - steps)) + steps;
//                }
//                maxEdge = Math.max(maxEdgeTmp, maxEdge);
//            }
//        }
//        return maxEdge * maxEdge;
//    }
}
