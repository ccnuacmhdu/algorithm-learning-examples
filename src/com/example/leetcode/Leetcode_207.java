package com.example.leetcode;

import java.util.*;

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
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] a : prerequisites) {
            inDegree[a[0]]++;
            List<Integer> list = map.getOrDefault(a[1], new ArrayList<>());
            list.add(a[0]);
            map.put(a[1], list);
        }
        LinkedList<Integer> que = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                que.add(i);
            }
        }
        int n = numCourses;
        while (!que.isEmpty()) {
            int e = que.poll();
            n--;
            if(map.containsKey(e)) {
                for (int i : map.get(e)) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        que.add(i);
                    }
                }
            }
        }
        return n == 0;
    }
}
