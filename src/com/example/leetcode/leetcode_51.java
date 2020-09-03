package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【N 皇后问题】
 * 暴力回溯法，每行只能放在某列，逐行放即可
 */
public class leetcode_51 {
    public List<List<String>> solveNQueens(int n) {
        int[] m = new int[n+1];
        List<List<String>> res = new ArrayList();
        List<List<Integer>> lists = new ArrayList<>();
        nQueen(1, m, lists);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < n; i++) {
            sb.append(".");
        }
        for(int i = 0; i < lists.size(); i++) {
            List<String> resOne = new ArrayList();
            for(int j = 0; j < lists.get(i).size(); j++) {
                sb.setCharAt(lists.get(i).get(j)-1, 'Q');
                resOne.add(sb.toString());
                sb.setCharAt(lists.get(i).get(j)-1, '.');
            }
            res.add(resOne);
        }
        return res;
    }
    public void nQueen(int row, int[] m, List<List<Integer>> lists) {
        if(row == m.length) {
            List<Integer> list = new ArrayList();
            for(int i = 1; i < m.length; i++) {
                list.add(m[i]);
            }
            lists.add(list);
        }
        for(int col = 1; col < m.length; col++) {
            if(check(row, col, m)) {
                m[row] = col;
                nQueen(row+1, m, lists);
                m[row] = 0;
            }
        }
    }
    public boolean check(int row, int col, int[] m) {
        for(int i = 1; i < row; i++) {
            if(m[i] == col || Math.abs(row - i) == Math.abs(col - m[i])) {
                return false;
            }
        }
        return true;
    }
}
