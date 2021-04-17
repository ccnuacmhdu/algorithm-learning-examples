package com.example.codeinterview.chapter04;

// [leetcode] 174. 地下城游戏
public class Code_04_10 {
    // 暴力递归，有几个样例会超时，待研究。。。
    public static int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0) {
            return 1;
        }
        return Math.max(process(dungeon, 0, 0, 0), 1);
    }

    private static int process(int[][] m, int x, int y, int cur) {
        if(x == m.length || y == m[0].length) {
            return 0;
        }

        int extra = cur + m[x][y] >= 1 ? 0 : (1 - (cur + m[x][y]));
        int newCur = cur + m[x][y] >= 1 ? (cur + m[x][y]) : 1;

        if(x == m.length - 1) {
            return process(m, x, y+1, newCur) + extra;
        }
        if(y == m[0].length - 1) {
            return process(m, x+1, y, newCur) + extra;
        }
        return Math.min(process(m, x+1, y, newCur), process(m, x, y+1, newCur)) + extra;
    }

}
