package com.example.backtracking;

public class Leetcode_79 {
    public boolean exist(char[][] board, String word) {
        boolean flag = false;
        boolean[][] vis = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                flag = flag || dfs(board, word, i, j, vis, 0);
                if(flag) return true;
            }
        }
        return flag;
    }
    private boolean dfs(char[][] board, String word, int x, int y, boolean[][] vis, int idx) {
        if(idx == word.length()) {
            return true;
        }
        if(x < 0 || y < 0 || x >= board.length || y >= board[0].length || vis[x][y] || board[x][y] != word.charAt(idx)) {
            return false;
        }
        vis[x][y] = true;
        boolean flag = false;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int i = 0; i < 4; i++) {
            int xx = dir[i][0] + x;
            int yy = dir[i][1] + y;
            flag = flag || dfs(board, word, xx, yy, vis, idx + 1);
            if(flag) return true;
        }
        // 回溯！
        vis[x][y] = false;
        return flag;
    }
}
