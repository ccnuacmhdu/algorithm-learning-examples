package com.example.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【矩阵的最大非负积】
 */
public class Leetcode_5521 {
    public int maxProductPath(int[][] grid) {
//        ArrayList<Long> res = new ArrayList<Long>();
//        f(grid, 0, 0, 1, res);
//        long max = Long.MIN_VALUE;
//        for(int i = 0; i < res.size(); i++) {
//            if(max < res.get(i)) {
//                max = res.get(i);
//            }
//        }
        long[] ret = memoryDfs(grid, 0, 0, new HashMap<>());
        long max = ret[0];
        if(max < 0) {
            return -1;
        }
        return (int)(max % (int)(1e9+7));
    }

    /**
     * 【方法一：暴力 DFS（投机取巧 0 剪枝）】
     * 暴力 DFS，求出所有情况下的乘积（超时/内存不够），可以投机取巧，0 剪枝。。有 0 再往后乘还是 0，就此收手
     */
    private void f(int[][] grid, int x, int y, long cur, List<Long> res) {
        int rows = grid.length;
        int cols = grid[0].length;
        if(x >= rows || y >= cols) {
            if(cur >= 0) {
                res.add(cur);
            }
            return;
        }
        // 0 剪枝
        if(cur == 0) {
            res.add(cur);
            return;
        }
        if(x == rows - 1) {
            f(grid, x, y+1, cur*grid[x][y], res);
        } else if(y == cols - 1) {
            f(grid, x+1, y, cur*grid[x][y], res);
        } else {
            f(grid, x + 1, y, cur*grid[x][y], res);
            f(grid, x, y + 1, cur*grid[x][y], res);
        }
    }

    /**
     * 【方法二：记忆化搜索】
     * 由于是负值的存在。。每次递归回退返回的是最大值和最小值。。
     *
     * @param grid
     * @param x
     * @param y
     */
    private long[] memoryDfs(int[][] grid, int x, int y, Map<String, Long> map) {
        int rows = grid.length;
        int cols = grid[0].length;
        if(x >= rows || y >= cols) {
            return new long[]{1, 1};
        }
        String key1 = x + "_" + y + "_max";
        String key2 = x + "_" + y + "_min";
        if(map.containsKey(key1) || map.containsKey(key2)) {
            return new long[]{map.get(key1), map.get(key2)};
        }
        // 0 剪枝
        if(grid[x][y] == 0) {
            map.put(key1, 0L);
            map.put(key2, 0L);
            return new long[]{0, 0};
        }
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        long[] res = new long[2];
        if(x == rows - 1) {
            res = memoryDfs(grid, x, y+1, map);
            max = Math.max(res[1] * grid[x][y], res[0] * grid[x][y]);
            min = Math.min(res[0] * grid[x][y], res[1] * grid[x][y]);
        } else if(y == cols - 1) {
            res = memoryDfs(grid, x+1, y, map);
            max = Math.max(res[1] * grid[x][y], res[0] * grid[x][y]);
            min = Math.min(res[0] * grid[x][y], res[1] * grid[x][y]);
        } else {
            int[][] dir = {{1, 0}, {0, 1}};
            for (int i = 0; i < dir.length; i++) {
                int xx = x + dir[i][0];
                int yy = y + dir[i][1];
                res = memoryDfs(grid, xx, yy, map);
                // 由于此种情况有两个方向的递归，要比较出两个方向中的最大最小值，所以必须这么啰嗦
                max = Math.max(res[0] * grid[x][y], max);
                max = Math.max(res[1] * grid[x][y], max);
                min = Math.min(res[0] * grid[x][y], min);
                min = Math.min(res[1] * grid[x][y], min);
            }
        }
        map.put(key1, max);
        map.put(key2, min);
        return new long[]{max, min};
    }
    public static void main(String[] args) {
        Leetcode_5521 leetcode_5521 = new Leetcode_5521();
        int[][] grid = {{1,-2,1},
                        {1,-2,1},
                        {3,-4,1}};
        System.out.println(leetcode_5521.maxProductPath(grid));
    }
}
