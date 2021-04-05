package com.example.codeinterview.chapter03;

import java.util.HashMap;
import java.util.Map;

public class Code_03_05 {
    private static class Node {
        public int v;
        public Node left;
        public Node right;
    }

    public static int getMaxLen(Node root, int sum) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0);   //
        return preOrder(root, sum, 0, 1, 0, sumMap);
    }

    /**
     * 在二叉树中找到累加和为指定值的最长路径长度
     *
     * 记录累加和与第一次出现该累加和的树高的映射关系，在遍历过程中依次确认以某节点结尾是否满足题目要求，不断更新最大值
     *
     * @param root 树根
     * @param sum 指定累加和的数值
     * @param preSum 直到上层的累加和
     * @param level 当前树高
     * @param maxLen 最大路径长度
     * @param sumMap 累加和 -> 第一次出现该累加和的树高
     */
    private static int preOrder(Node root, int sum, int preSum, int level, int maxLen, Map<Integer, Integer> sumMap) {
        if(root == null) {
            return maxLen;
        }
        int curSum = preSum + root.v;
        if(!sumMap.containsKey(curSum)) {
            sumMap.put(curSum, level);
        }
        if(sumMap.containsKey(curSum - sum)) {
            maxLen = Math.max(maxLen, level - sumMap.get(curSum - sum));
        }
        maxLen = preOrder(root.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(root.right, sum, curSum, level + 1, maxLen, sumMap);
        // 参考： https://search.bilibili.com/all?keyword=%E5%9C%A8%E4%BA%8C%E5%8F%89%E6%A0%91%E4%B8%AD%E6%89%BE%E5%88%B0%E7%B4%AF%E5%8A%A0%E5%92%8C%E4%B8%BA%E6%8C%87%E5%AE%9A%E5%80%BC%E7%9A%84%E6%9C%80%E9%95%BF%E8%B7%AF%E5%BE%84%E9%95%BF%E5%BA%A6&from_source=nav_search_new
        if(level == sumMap.get(curSum)) {
            // 回溯
            sumMap.remove(curSum);
        }
        return maxLen;
    }
}
