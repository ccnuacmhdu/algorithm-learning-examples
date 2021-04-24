package com.example.codeinterview.chapter08;

// [leetcode] 剑指 Offer 29. 顺时针打印矩阵
public class Code_08_01 {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] res = new int[rows*cols];
        boolean[][] vis = new boolean[rows][cols];
        int i = 0;
        int j = 0;
        int index = 0;
        while(index < rows*cols) {
            while(j < cols && !vis[i][j]) {
                vis[i][j] = true;
                res[index++] = matrix[i][j++];
            }
            j--;
            i++;
            while(i < rows && !vis[i][j]) {
                vis[i][j] = true;
                res[index++] = matrix[i++][j];
            }
            i--;
            j--;
            while(j >= 0 && !vis[i][j]) {
                vis[i][j] = true;
                res[index++] = matrix[i][j--];
            }
            j++;
            i--;
            while(i >= 0 && !vis[i][j]) {
                vis[i][j] = true;
                res[index++] = matrix[i--][j];
            }
            i++;
            j++;
        }
        return res;
    }
}
