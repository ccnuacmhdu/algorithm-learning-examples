package com.example.leetcode100hot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leetcode_207 {
    /**
     * 拓扑排序
     *
     * 1. 入度为 0 的节点入队列
     * 2. 广度优先搜索，直到队列为空，判定是否得到了所有节点
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for(int[] a: prerequisites) {
            edges.get(a[1]).add(a[0]);
            inDegree[a[0]]++;
        }
        LinkedList<Integer> que = new LinkedList<>();
        for(int i = 0; i < inDegree.length; i++) {
            if(inDegree[i] == 0) {
                que.add(i);
            }
        }
        int cnt = 0;
        while (!que.isEmpty()) {
            int n = que.poll();
            cnt++;
            for(int x: edges.get(n)) {
                inDegree[x]--;
                if(inDegree[x] == 0) {
                    que.add(x);
                }
            }
        }
        return cnt == numCourses;
    }
}
