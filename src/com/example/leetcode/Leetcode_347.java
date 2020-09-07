package com.example.leetcode;

import java.util.*;

/**
 * 【前 K 个高频元素】
 */
public class Leetcode_347 {
    public class Node {
        public int num;
        public int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    /**
     * 【方式一：HashMap 词频统计】
     * 对 Node 按照 cnt 大小排序的时间复杂度应该就达到了 O(NlogN)，题目要求比这个更优。。
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent_01(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i])+1);
            } else {
                map.put(nums[i], 1);
            }
        }
        List<Node> list = new ArrayList<>();
        for(Integer key: map.keySet()) {
            list.add(new Node(key, map.get(key)));
        }
        Collections.sort(list, (Node o1, Node o2) -> o2.cnt - o1.cnt);
        int[] res = new int[k];
        for(int i = 0; i < k; i++) {
            res[i] = list.get(i).num;
        }
        return res;
    }
    /**
     * 【方式二：使用小根堆优化到 O(NlogK)】
     */
    public int[] topKFrequent_02(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List<Node> list = new ArrayList<>();
        for(Integer key: map.keySet()) {
            list.add(new Node(key, map.get(key)));
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((Node o1, Node o2) -> o1.cnt - o2.cnt);
        for(Node node: list) {
            if(pq.size() == k) {
                if(pq.peek().cnt < node.cnt) {
                    pq.poll();
                    pq.add(node);
                }
            } else {
                pq.add(node);
            }
        }
        int[] res = new int[k];
        int index = 0;
        while (index < k) {
            res[index++] = pq.poll().num;
        }
        return res;
    }
}
