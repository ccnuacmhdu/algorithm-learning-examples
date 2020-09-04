package com.example.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 特别说明：本代码参考《程序员代码面试指南：IT名企算法与数据结构题目最优解》作者左程云相关代码
 * 【项目利润最大问题】
 *
 * 输入：正数数组 costs、正数数组 profits、正数 k、正数 m
 * costs[i]表示 i 号项目的花费
 * profits[i]表示 i 号项目在扣除花费之后还能挣到的钱（利润）
 * k 表示你只能串行的最多做 k 个项目
 * m 表示你初始的资金
 *
 * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
 *
 * 输出：你最后获得的最大钱数。
 *
 * 【贪心策略】
 * 1. 先把所有项目按照花费大小进入小根堆；
 * 2. 然后把小根堆中能够做的项目（花费小于等于当前资金）按照利润大小进入大根堆；
 * 3. 取大根堆堆顶项目做了。
 * 重复该步骤 1 - 3，直到没有可选的项目或已选项目数等于 K。
 */
public class GreedyExample05 {
    public static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }

    }

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Node[] nodes = new Node[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            nodes[i] = new Node(Profits[i], Capital[i]);
        }

        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < nodes.length; i++) {
            minCostQ.add(nodes[i]);
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }
}





