package com.example.offer;

import java.util.LinkedList;

public class JZ_13 {
    // DFS
    public int movingCount(int m, int n, int k) {
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        return move(0, 0, new boolean[m][n], m, n, k, dir);
    }
    private int move(int x, int y, boolean[][] vis, int m, int n, int k, int[][] dir) {
        if(!check(x, y, vis, m, n, k)) {
            return 0;
        }

        vis[x][y] = true;
        int num = 1;

        for(int i = 0; i < 4; i++) {
            int newX = x + dir[i][0];
            int newY = y + dir[i][1];
            num += move(newX, newY, vis, m, n, k, dir);
        }
        return num;
    }

    // BFS
//    public int movingCount(int m, int n, int k) {
//        LinkedList<int[]> que = new LinkedList<>();
//        que.add(new int[]{0, 0});
//        int num = 0;
//        boolean[][] vis = new boolean[m][n];
//        int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
//        while(!que.isEmpty()) {
//            int[] arr = que.poll();
//            int x = arr[0];
//            int y = arr[1];
//            if(check(x, y, vis, m, n, k)) {
//                vis[x][y] = true;
//                num++;
//                for(int i = 0; i < dir.length; i++) {
//                    int newX = x + dir[i][0];
//                    int newY = y + dir[i][1];
//                    que.add(new int[]{newX, newY});
//                }
//            }
//        }
//        return num;
//    }


    private boolean check(int x, int y, boolean[][] vis, int m, int n, int k) {
        if(x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) {
            return false;
        }
        int digitSum = getDigitSum(x) + getDigitSum(y);
        return digitSum <= k;
    }
    private int getDigitSum(int n) {
        int s = 0;
        while(n != 0) {
            s += n % 10;
            n /= 10;
        }
        return s;
    }
}
