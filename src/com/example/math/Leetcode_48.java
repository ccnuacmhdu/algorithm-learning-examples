package com.example.math;

public class Leetcode_48 {
    // (r, c) -> (c, n-1-r)
    // n = 3, (0, 0), (0, 1) 分别出发
    // n = 4, (0, 0), (0, 1), (1, 0), (1, 1) 分别出发
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int r = 0; r < n / 2; r++) {
            for(int c = 0; c < (n + 1) / 2; c++) {
                int t = matrix[c][n-1-r];
                matrix[c][n-1-r] = matrix[r][c];
                matrix[r][c] = matrix[n-1-c][r];
                matrix[n-1-c][r] = matrix[n-1-r][n-1-c];
                matrix[n-1-r][n-1-c] = t;
            }
        }
    }
}
