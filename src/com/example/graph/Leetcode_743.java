package com.example.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Dijkstra
 */
public class Leetcode_743 {
    // 优先队列优化（bfs）
    public int networkDelayTime(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE / 2;

        int[] dis = new int[n + 1];
        Arrays.fill(dis, INF);
        dis[k] = 0;

        boolean[] vis = new boolean[n + 1];

        int[][] mp = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(mp[i], INF);
        }
        for(int i = 0; i < times.length; i++) {
            int x = times[i][0];
            int y = times[i][1];
            int w = times[i][2];
            mp[x][y] = w;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] x, int[] y) {
                return x[1] - y[1];
            }
        });

        pq.add(new int[]{k, 0});

        while (!pq.isEmpty()) {
            int[] a = pq.poll();
            int v = a[0];
            int d = a[1];
            for(int i = 1; i <= n; i++) {
                if(!vis[i] && d + mp[v][i] < dis[i]) {
                    dis[i] = d + mp[v][i];
                    pq.add(new int[]{i, dis[i]});
                }
            }
            vis[v] = true;
        }

        int max = -1;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, dis[i]);
        }
        return max == INF ? -1 : max;
    }

    // 常规法
//    public int networkDelayTime(int[][] times, int n, int k) {
//        int INF = Integer.MAX_VALUE / 2;
//
//        int[] dis = new int[n + 1];
//        boolean[] vis = new boolean[n + 1];
//        Arrays.fill(dis, INF);
//
//        int[][] mp = new int[n + 1][n + 1];
//        for(int i = 0; i < n + 1; i++) {
//            Arrays.fill(mp[i], INF);
//        }
//
//        for(int i = 0; i < times.length; i++) {
//            int x = times[i][0];
//            int y = times[i][1];
//            int w = times[i][2];
//            mp[x][y] = w;
//        }
//
//        dis[k] = 0;
//        for(int i = 1; i <= n; i++) {
//            int min = INF;
//            int pos = -1;
//            for(int j = 1; j <= n; j++) {
//                if(!vis[j] && dis[j] < min) {
//                    pos = j;
//                    min = dis[j];
//                }
//            }
//            if(pos == -1) break;
//            vis[pos] = true;
//            for(int j = 1; j <= n; j++) {
//                if(!vis[j] && dis[pos] + mp[pos][j] < dis[j]) {
//                    dis[j] = dis[pos] + mp[pos][j];
//                }
//            }
//        }
//        int max = 0;
//        for(int i = 1; i <= n; i++) {
//            max = Math.max(max, dis[i]);
//        }
//        return max == INF ? -1 : max;
//    }

}
