package com.example.hj;

import java.util.*;

/**
 * HJ43 迷宫问题
 */
public class HJ43 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[][] matrix = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
            List<String> path = new ArrayList<>();
            dfs(matrix, 0, 0, path);
            for(int i = path.size() - 1; i >= 0; i--) {
                System.out.println(path.get(i));
            }
        }
    }
    private static boolean dfs(int[][] matrix, int i, int j, List<String> path) {
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 1) {
            return false;
        }
        if(i == matrix.length - 1 && j == matrix[0].length - 1) {
            path.add("(" + i + "," + j + ")");
            return true;
        }
        matrix[i][j] = 1;
        boolean ret = dfs(matrix, i + 1, j, path)
                || dfs(matrix, i - 1, j, path)
                || dfs(matrix, i, j + 1, path)
                || dfs(matrix, i, j - 1, path);
        if(ret) {
            path.add("(" + i + "," + j + ")");
        }
        matrix[i][j] = 0;
        return ret;
    }
}
