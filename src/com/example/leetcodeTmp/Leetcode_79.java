package com.example.leetcodeTmp;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_79 {
    /**
     * 【方式一：暴力（超时）】
     * 求出所有路径，看看是否包含 word
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist_00(char[][] board, String word) {
        List<String> strs = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                boolean[][] vis = new boolean[board.length][board[0].length];
                f0(i, j, board, word, new StringBuilder(), vis, strs);
            }
        }
        for(String s : strs) {
            System.out.println(s);
        }
        return strs.contains(word);
    }
    private void f0(int x, int y, char[][] board, String word, StringBuilder sb, boolean[][] vis, List<String> strs) {
        vis[x][y] = true;
        sb.append(board[x][y]);
        strs.add(sb.toString());
        if(check0(x+1, y, board.length, board[0].length, vis)) {
            f0(x + 1, y, board, word, sb, vis, strs);
        }
        if(check0(x-1, y, board.length, board[0].length, vis)) {
            f0(x-1, y, board, word, sb, vis, strs);
        }
        if(check0(x, y+1, board.length, board[0].length, vis)) {
            f0(x, y+1, board, word, sb, vis, strs);
        }
        if(check0(x, y-1, board.length, board[0].length, vis)) {
            f0(x, y-1, board, word, sb, vis, strs);
        }
        // 回溯
        vis[x][y] = false;
        sb.deleteCharAt(sb.length()-1);
    }
    private boolean check0(int x, int y, int row, int col, boolean[][] vis) {
        if(x >= 0 && x < row && y >= 0 && y < col && !vis[x][y]) {
            return true;
        }
        return false;
    }

    /**
     * 【方式二：基于方式一优化】
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        // 因为后面用了回溯，故而只需一个 vis，不用每次 DFS 都搞一个新的 vis
        boolean[][] vis = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                boolean flag = f(i, j, board, word, vis, 0);
                if(flag) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean f(int x, int y, char[][] board, String word, boolean[][] vis, int index) {
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || vis[x][y]) {
            return false;
        }
        if (board[x][y] != word.charAt(index)) {
            return false;
        }
        if (board[x][y] == word.charAt(index) && index == word.length() - 1) {
            return true;
        }
        vis[x][y] = true;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean res = false;
        // 下面其实改成四种情况的 || 在一起，利用 || 短路性质更快些
        for(int i = 0; i < 4; i++) {
            boolean flag = f(x + dir[i][0], y + dir[i][1], board, word, vis, index+1);
            res = flag;
            if(res) {
                break;
            }
        }
        // 回溯，其实不管 board[x][y] 是否使用，都回溯，最终 vis[x][y] = false
        vis[x][y] = false;
        return res;
    }

    public static void main(String[] args) {
        Leetcode_79 leetcode_79 = new Leetcode_79();
        char[][] board = {{'A','B'},
                          {'S','F'}};
        String word = "ABFS";
//        boolean exists = leetcode_79.exist_00(board, word);
        boolean exist = leetcode_79.exist(board, word);
//        System.out.println(exists);
        System.out.println(exist);
    }
}
