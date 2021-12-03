package com.example.dynamicProgramming;

import java.util.Arrays;

public class Leetcode_64 {

    // 暴力递归（超时）
//    public int minPathSum(int[][] grid) {
//        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
//            return 0;
//        }
//        return process(grid, grid.length - 1, grid[0].length - 1);
//    }
//    private int process(int[][] grid, int x, int y) {
//        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
//            return 4000001;
//        }
//        if(x == 0 && y == 0) {
//            return 0;
//        }
//        return grid[x][y] + Math.min(
//                process(grid, x, y - 1),
//                process(grid, x - 1, y)
//        );
//    }

    // 记忆化搜索
//    public int minPathSum(int[][] grid) {
//        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
//            return 0;
//        }
//        int MAX_VALUE = 4000001;
//        int m = grid.length, n = grid[0].length;
//        int[][] map = new int[m][n];
//        for(int i = 0; i < map.length; i++) {
//            Arrays.fill(map[i], MAX_VALUE);
//        }
//        return process(grid, m - 1, n - 1, map, MAX_VALUE);
//    }
//    private int process(int[][] grid, int x, int y, int[][] map, int max) {
//        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
//            return max;
//        }
//        if(x == 0 && y == 0) {
//            return grid[0][0];
//        }
//        if(map[x][y] != max) {
//            return map[x][y];
//        }
//        return map[x][y] = grid[x][y] + Math.min(
//                process(grid, x - 1, y, map, max),
//                process(grid, x, y - 1, map, max)
//        );
//    }

    // 动态规划
//    public int minPathSum(int[][] grid) {
//        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
//            return 0;
//        }
//        int m = grid.length, n = grid[0].length;
//        int[][] dp = new int[m][n];
//        dp[0][0] = grid[0][0];
//        for(int j = 1; j < n; j++) {
//            dp[0][j] = dp[0][j-1] + grid[0][j];
//        }
//        for(int i = 1; i < m; i++) {
//            dp[i][0] = dp[i-1][0] + grid[i][0];
//        }
//        for(int i = 1; i < m; i++) {
//            for(int j = 1; j < n; j++) {
//                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
//            }
//        }
//        return dp[m-1][n-1];
//    }

    // 动态规划（状态压缩）
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for(int j = 1; j < n; j++) {
            dp[j] = dp[j-1] + grid[0][j];
        }
        for(int i = 1; i < m; i++) {
            dp[0] = grid[i][0] + dp[0];
            for(int j = 1; j < n; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j-1]);
            }
        }
        return dp[n-1];
    }

}
