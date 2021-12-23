package com.example.leetcodeTmp;

import java.util.*;

/**
 * 【解数独】
 */
public class Leetcode_37 {
    /**
     * 回溯法
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        // 1. 找到所有空格（待填数的点）
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    points.add(new Point(i, j));
                }
            }
        }
        // 2. 暴力递归依次把空格填上
        dfs(0, points, board);

    }
    private boolean dfs(int loc, List<Point> points, char[][] board) {
        if(loc == points.size()) {
            return true;
        }
        for(int i = 1; i <= 9; i++) {
            if(check(points.get(loc), i, board)) {
                // 如果 loc 位置都可以了，并且从 loc+1 位置之后的又都可以，那么以 loc 开始的就都可以了
                board[points.get(loc).row][points.get(loc).col] = (char) (i + '0');
                if(dfs(loc+1, points, board)) {
                    return true;
                }
                board[points.get(loc).row][points.get(loc).col] = '.';
            }
        }
        return false;
    }
    // 检查 point 点处是否可以放置 v
    private boolean check(Point point, int v, char[][] board) {
        int row = point.row;
        int col = point.col;
        for(int i = 0; i < 9; i++) {
            if(i != row && board[i][col] - '0' == v) {
                return false;
            }
            if(i != col && board[row][i] - '0' == v) {
                return false;
            }
        }
        for(int i = row/3*3; i < row/3*3+3; i++) {
            for(int j = col/3*3; j <col/3*3+3; j++) {
                if(i != row && j != col && board[i][j] - '0' == v) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 记录某点的横纵坐标
     */
    private class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
