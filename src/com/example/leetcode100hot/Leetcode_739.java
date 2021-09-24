package com.example.leetcode100hot;

import java.util.Arrays;
import java.util.Stack;

public class Leetcode_739 {
    /*
    * 单调栈
    * */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int pop = stack.pop();
                ans[pop] = i - pop;
            }
            stack.push(i);
        }
        return ans;
    }

    /*
     * 常规法
     *
     * 题目中的 [30, 100] 的温度范围提示，可以记录每个温度最早出现的位置，从后
     * 往前遍历数组，不断更新每个温度最早出现的位置。大于当前温度的最早位置，只需
     * 找到大于当前温度的最小位置即可。
     *
     * */
    public int[] dailyTemperatures2(int[] temperatures) {
        final int LEN_MAX = temperatures.length;
        final int TEM_MAX = 101;
        int[] ans = new int[LEN_MAX];
        int[] first = new int[TEM_MAX];
        Arrays.fill(first, LEN_MAX);
        for(int i = LEN_MAX - 1; i >= 0; i--) {
            int tem = temperatures[i];
            int minLoc = LEN_MAX;
            for(int t = tem + 1; t < TEM_MAX; t++) {
                if(first[t] != LEN_MAX) {
                    minLoc = Math.min(first[t], minLoc);
                }
            }
            if(minLoc != LEN_MAX) {
                ans[i] = minLoc - i;
            }
            first[tem] = i;
        }
        return ans;
    }
}
