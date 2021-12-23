package com.example.leetcodeTmp;

/**
 * 【冗余连接 II】
 */
public class Leetcode_685 {
    /**
     * 1. “有根树”要满足每个节点只有唯一的父亲，根节点没有父节点，并且只有一个根节点，所有其他节点都是该根节点的后继。
     * 2. N 个节点 N 条边，如果是无向图必定有环，而本题中是有向图，题目中的两个例子展示了有环和存在某节点有两个父亲
     *    的情况。其实所有情况就是这两种情况的组合了。抓住”有根树“的定义，限制的多，其实情况蛮少。。（可对每种情况画
     *    图，可先画一个”有根树“，再添加一条边。特别注意所有其他节点都是根节点的后继）
     * 3. 各种情况分析。
     *    1）存在某节点有两个父亲，且无环（把导致有两个父亲的后出现的边删掉即可）
     *    2）存在某节点有两个父亲，且有环（干掉环上的父亲边。查看第一个父亲节点是否在该环上，若是，就删该条边，否则删第二个父亲节点的边）
     *    3）不存在某节点有两个父亲，必定有环（且包含根节点）（删环上最后出现的边）
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        int[] parent = new int[N+1];

        // 记录有两个父亲的某节点的第二个父亲对应的边
        int[] secondEdge = null;
        int firstParent = 0;

        for(int i = 0; i < edges.length; i++) {
            if(parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                secondEdge = edges[i];
                firstParent = parent[edges[i][1]];
            }
        }

        int[] res = secondEdge;
        // 1. 不存在有两个父亲的节点（环必含根，从任意一个节点都能把环转一圈）
        if(secondEdge == null) {
            res = findCircleLastEdge(1, N, parent, edges);
        } else {
            // 2. 存在有两个父亲的节点，两个父亲必须干掉其中一个（注意第二个父亲边未记录到 parent 数组）。
            // 2.1. 无环，直接干掉第二个父亲导致的那条边。
            // 2.2. 有环，干掉在环上的那个父亲边。
            // 利用第一个父亲去找环，若找不到环，就干掉 secondEdge；若能找到环，干掉第一个父亲边
            if(findCircleLastEdge(firstParent, N, parent, edges) != null) {
                res[0] = firstParent;
            }
        }
        return res;
    }
    private int[] findCircleLastEdge(int v, int n, int[] parent, int[][] edges) {
        boolean[] vis = new boolean[n+1];
        while (v != 0) {
            vis[v] = true;
            if(vis[parent[v]]) {
                // 有环，就找到环上最后出现的边
                for(int i = n - 1; i >= 0; i--) {
                    if(vis[edges[i][0]] && vis[edges[i][1]]) {
                        return edges[i];
                    }
                }
            }
            v = parent[v];
        }
        return null;
    }
}
