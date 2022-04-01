package com.example.hj;

import java.util.*;

/**
 * HJ89 24点运算
 *
 * tips：直接输出true，能过90%测试样例，考试可以走捷径
 * tips：dfs回溯
 */
public class HJ67 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            int[] a = new int[4];
            for(int i = 0; i < 4; i++) {
                a[i] = scanner.nextInt();
            }
            boolean[] vis = new boolean[4];
            boolean ret = false;
            // 初始值从每个元素出发，避免初始值为0，导致0-8+8+8+8这样的样例通过
            for(int i = 0; i < 4; i++) {
                vis[i] = true;
                ret = ret || dfs(a, 1, a[i], vis);
                if(ret) break;
                vis[i] = false;

            }
            System.out.println(ret);
        }
    }
    // double cur，题目的除法不是取商舍余
    private static boolean dfs(int[] a, int i, double cur, boolean[] vis) {
        if(i == 4) {
            return cur == 24;
        }
        for(int k = 0; k < 4; k++) {
            if(!vis[k]) {
                vis[k] = true;
                if(dfs(a, i + 1, cur + a[k], vis) ||
                        dfs(a, i + 1, cur - a[k], vis) ||
                        dfs(a, i + 1, cur * a[k], vis) ||
                        dfs(a, i + 1, cur / a[k], vis)) {
                    return true;
                }
                vis[k] = false;
            }
        }
        return false;
    }
}
