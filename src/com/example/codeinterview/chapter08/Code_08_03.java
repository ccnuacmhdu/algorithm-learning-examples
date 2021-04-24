package com.example.codeinterview.chapter08;

public class Code_08_03 {
    // 找好起点，形成单调趋势。。。不要老抓住(0, 0)点作为起点，那就死结了。。。
    public boolean isContains(int[][] matrix, int K) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == K) {
                return true;
            } else if (matrix[row][col] > K) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
