package com.example.greedy;

import java.util.PriorityQueue;

/**
 * 【金条分割问题】
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为 20 的金条，不管切成长度多大的两半，都要花费 20 个铜板。
 * 一群人想整分整块金条，怎么分最省铜板? 例如，给定数组 {10,20,30}，代表一共三个人，整块金条长度为 10+20+30=60。
 * 金条要分成 10，20，30 三个部分。如果先把长度 60 的金条分成 10 和 50，花费 60；再把长度 50 的金条分成 20 和 30，花费 50；
 * 一共花费 110 铜板。但是如果先把长度 60 的金条分成 30 和 30，花费 60；再把长度 30 金条分成 10 和 20，花费 30；一共花费 90
 * 铜板。输入一个数组，返回分割的最小代价。
 *
 * 【贪心策略】
 * 每次取出最小的两个求和，并把这两个的和放进去，然后继续每次取出最小的两个……依次重复这个过程（哈夫曼编码）
 */
public class GreedyExample02 {
    public static int leastMoney(int[] a) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i = 0; i < a.length; i++){
            pq.add(a[i]);
        }
        int sum = 0;
        int cur = 0;
        while(pq.size() >= 2){
            cur = pq.poll() + pq.poll();
            sum += cur;
            pq.add(cur);
        }
        return sum;
    }
}
