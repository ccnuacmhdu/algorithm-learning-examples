package com.example.math;

public class Leetcode_48 {
    // (0, 0) -> (0, 2) -> (2, 2) -> (2, 0) -> (0, 0)
    // (0, 1) -> (1, 2) -> (2, 1) -> (1, 0) -> (0, 1)
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n / 2; i++) {
            for(int j = 0; j < (n + 1) / 2; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i]; // j + (n - 1 - j) = n - 1, i = i
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j]; // n - 1 - j = n - 1 -j, i + (n - 1 - i) = n - 1
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i]; // n - 1 - i = n - 1 - i, (n - 1 - j) + j = n - 1
                matrix[j][n - 1 - i] = t;
            }
        }
    }
}
