package com.example.monotonicStack;

import java.util.Stack;

public class Leetcode_739 {
    // 单调栈（优化）
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

    // 单调栈
//    public int[] dailyTemperatures(int[] temperatures) {
//        int[] ans = new int[temperatures.length];
//        Stack<Integer> minStack = new Stack<>();
//        for(int i = 0; i < temperatures.length; i++) {
//            if(minStack.isEmpty() || temperatures[i] <= temperatures[minStack.peek()]) {
//                minStack.push(i);
//            } else {
//                while(!minStack.isEmpty() && temperatures[i] > temperatures[minStack.peek()]) {
//                    int pop = minStack.pop();
//                    ans[pop] = i - pop;
//                }
//                minStack.push(i);
//            }
//        }
//        // 数组元素默认初始为 0，下面可省去
//        while(!minStack.isEmpty()) {
//            ans[minStack.pop()] = 0;
//        }
//        return ans;
//    }
}
