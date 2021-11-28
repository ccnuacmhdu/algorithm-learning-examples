package com.example.codeInterview.chapter04;

public class Code_04_02 {

    // 方法三：动态规划（状态压缩）
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int xL = grid.length;
        int yL = grid[0].length;
        int[] dp = new int[yL];

        dp[0] = grid[0][0];
        for(int i = 1; i < yL; i++) {
            dp[i] = dp[i-1] + grid[0][i];
        }
        for(int i = 1; i < xL; i++) {
            dp[0] += grid[i][0];
            for(int j = 1; j < yL; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j-1]);
            }
        }
        return dp[yL-1];
    }

    // 方法二：动态规划
    /*public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int xL = grid.length;
        int yL = grid[0].length;
        int[][] dp = new int[xL][yL];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < xL; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int i = 1; i < yL; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for(int i = 1; i < xL; i++) {
            for(int j = 1; j < yL; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[xL-1][yL-1];
    }*/

    // 方法一：记忆化搜索
    /*public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int xL = grid.length;
        int yL = grid[0].length;
        int[][] map = new int[xL][yL];
        for(int i = 0; i < xL; i++) {
            Arrays.fill(map[i], -1);
        }
        return dfs(grid, xL - 1, yL - 1, map);
    }
    private int dfs(int[][] a, int x, int y, int[][] map) {
        if(x == 0 && y == 0) {
            return a[0][0];
        }
        if(x < 0 || x >= a.length || y < 0 || y >= a[0].length) {
            return 10000000;
        }
        if(map[x][y] != -1) {
            return map[x][y];
        }
        return map[x][y] = a[x][y] + Math.min(dfs(a, x-1, y,  map), dfs(a, x, y-1, map));
    }*/
}
