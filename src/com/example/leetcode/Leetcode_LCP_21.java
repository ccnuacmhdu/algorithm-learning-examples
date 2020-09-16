package com.example.leetcode;

import java.util.*;
import java.util.ArrayList;

/**
 * 特别说明：本代码来源 Leetcode 中文官网《LCCUP ‘20 力扣杯 秋季编程大赛 - 个人赛》第二名所写，
 * 第二名作者地址 https://leetcode-cn.com/u/tian-tang-6/
 *
 * 整体来说，就是以 A 为根节点建立了一个 "树"，其他所有节点都是 A 的孩子，以此建模解决问题。
 * - 找到环上所有节点
 * - 求出每个节点到根节点最短距离
 * - 求出每个节点能通过孩子节点延伸到的距离根节点的最远距离（要远离根节点方向往孩子方向跑）
 * - 求出每个节点及其孩子节点是否能到达环（其孩子有在环上的，因为孩子方向是背离根 A 方向，必定可先于 A 到环）
 * - 利用每个节点的父母节点，父母节点到某节点的距离加 1 小于某节点到根 A 的距离，这样的父母节点可以帮助 B 到达距离 A 更远的节点或入环
 */
public class Leetcode_LCP_21 {

    private class Node {
        // 节点id
        int id;
        // 节点的父母
        Node parent;
        // 节点的孩子们
        List<Node> virtual = new ArrayList<>();
        // 节点到根节点的最短距离
        int depth = (int) 1e9;
        int depthest;
        boolean containCircle;
        // 节点是否在环上
        boolean onCircle;
        // 节点是否还在递归栈中
        boolean inStack;
        // 节点是否被访问过
        boolean visited;
        // 节点的邻接节点们
        List<Node> adj = new ArrayList<>();
    }

    public int chaseGame(int[][] edges, int startA, int startB) {
        int n = edges.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            nodes[i].id = i;
        }
        for (int[] e : edges) {
            int u = e[0] - 1;
            int v = e[1] - 1;
            nodes[u].adj.add(nodes[v]);
            nodes[v].adj.add(nodes[u]);
        }
        // 1. 找到所有在环上的节点
        Deque<Node> circle = new ArrayDeque<>();
        Node root = nodes[startA - 1];
        findCircle(root, null, circle);
        for (Node node : circle) {
            node.onCircle = true;
        }
        // 2. 每个节点到根节点的最短距离，根就是 A
        bfs(root);
        // 3. 确认每个节点的 depthest 和 containCircle
        dfs(root, null);

        Node x = nodes[startB - 1];
        boolean onCircle = false;
        // B 能跑到的离 A 最远的距离
        int depthest = 1;
        // x.depth - node.depth + 1 是 B 到 node 的距离加1，node.length 是 A 到 node 的距离
        // 比如入环节点到 B 的距离是 1，到 A 的距离是 2，那么 A 先走一步，此时 A 和 B 到入环节点的距离
        // 就一样了，B 走一步到入环节点，A 走一步也到了入环节点，A 抓住了 B，所以要想抓不住，就得满足 B
        // 到入环节点距离加 1 小于 A 到入环节点距离。
        for (Node node = x; x.depth - node.depth + 1 < node.depth; node = node.parent) {
            //System.out.println(x.id);
            depthest = Math.max(depthest, node.depthest);
            // 因为 depthest 和 containCircle 本身就是取得自身和 孩子的，故不判孩子了
            // 如果满足条件的 B 可达的所有父亲节点中有在环里的，那么 B 一定可以在 A 追上他之前入环
            onCircle = onCircle || node.containCircle;
        }

        if (onCircle && circle.size() > 3) {
            return -1;
        }
        return depthest;
    }

    // 每个节点到根节点的最短距离
    public void bfs(Node root) {
        Deque<Node> trace = new ArrayDeque<>();
        trace.addLast(root);
        root.depth = 0;
        root.parent = null;

        while (!trace.isEmpty()) {
            Node head = trace.removeFirst();
            for (Node node : head.adj) {
                if (node.depth > head.depth + 1) {
                    node.depth = head.depth + 1;
                    node.parent = head;
                    head.virtual.add(node);
                    trace.addLast(node);
                }
            }
        }
    }
    // 每个节点 P 利用自身及其孩子们所能达到的距根节点 A 的最远距离就是自己的 depthest，其实就是 P 节点所能到达的
    // 距离根节点 A 的最远的节点 X，X 节点距离根节点 A 的距离是 depthest
    // 每个节点 P 利用自身及其孩子们只要有在环上的，那么节点 P 的 containCircle 就是 true
    public void dfs(Node root, Node parent) {
        root.containCircle = root.onCircle;
        root.depthest = root.depth;
        for (Node node : root.virtual) {
            if (node == parent) {
                continue;
            }
            dfs(node, root);
            root.containCircle = root.containCircle || node.containCircle;
            root.depthest = Math.max(root.depthest, node.depthest);
        }
    }

    // parent 是 root 的父母节点，circle 是最终得到的环
    public boolean findCircle(Node root, Node parent, Deque<Node> circle) {
        if (root.visited) {
            if (root.inStack) {
                // 去除入环节点之前的不在环上的节点
                while (circle.peekFirst() != root) {
                    circle.removeFirst();
                }
                return true;
            }
            return false;
        }
        circle.addLast(root);
        root.visited = root.inStack = true;
        for (Node node : root.adj) {
            if (node == parent) {
                continue;
            }
            if (findCircle(node, root, circle)) {
                return true;
            }
        }
        // 去除其他不在环上的节点
        circle.removeLast();
        root.inStack = false;
        return false;
    }
}
