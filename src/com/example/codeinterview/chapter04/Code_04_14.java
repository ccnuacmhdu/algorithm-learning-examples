package com.example.codeinterview.chapter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code_04_14 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>(n);
        int[] queens = new int[n];
        process(0, queens, res);
        return res;
    }

    private void process(int r, int[] queens, List<List<String>> res) {
        if(r == queens.length) {
            List<String> list = generateBoard(queens);
            res.add(list);
            return;
        }
        for (int c = 0; c < queens.length; c++) {
            if(check(r, c, queens)) {
                queens[r] = c;
                process(r+1, queens, res);
                queens[r] = c;
            }
        }
    }
    private boolean check(int r, int c, int[] m) {
        for(int i = 0; i < r; i++) {
            if(m[i] == c || Math.abs(r - i) == Math.abs(c - m[i])) {
                return false;
            }
        }
        return true;
    }
    public List<String> generateBoard(int[] queens) {
        List<String> board = new ArrayList<>();
        int n = queens.length;
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
