package com.example.codeinterview.chapter01;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.List;

/**
 * 单调栈结构
 */
public class Code_01_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[][] res = getNearestLess(arr);
        StringBuilder sb = new StringBuilder();
        if(res != null) {
            for(int i = 0; i < res.length; i++) {
//                System.out.println(res[i][0] + " " + res[i][1]); // 此效率太低，会导致超时。。
                sb.append(res[i][0]).append(" ").append(res[i][1]).append("\n");
            }
        }
        System.out.println(sb);
    }

    /**
     * arr 无重复元素
     * 用一个单调栈存储 arr 元素下标，保证从栈底到栈顶对应的 arr 元素是单调递增的，
     * 每次取出栈顶和当前 arr 元素比较，前者大于后者，就可记录栈顶对应的结果了。
     *
     * @param
     * @return
     */
    public static int[][] getNearestLessNoRepeat(int[] arr) {
        if(arr == null || arr.length < 1) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftNearestLess = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftNearestLess;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        // 最后若单调栈不空，必定是自底向上单调递增的情况，每个索引对应的元素右边必定没有比其小的
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftNearestLess = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftNearestLess;
            res[popIndex][1] = -1;
        }
        return res;
    }

    /**
     * arr 可能有重复元素
     *
     * @param arr
     * @return
     */
    public static int[][] getNearestLess(int[] arr) {
        if(arr == null || arr.length < 1) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for(int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIndexes = stack.pop();
                int leftNearestLess = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for(int j = 0; j < popIndexes.size(); j++) {
                    res[popIndexes.get(j)][0] = leftNearestLess;
                    res[popIndexes.get(j)][1] = i;
                }
            }
            if(!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> sameIndexes = new ArrayList<>();
                sameIndexes.add(i);
                stack.push(sameIndexes);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIndexes = stack.pop();
            int leftNearestLess = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for(int j = 0; j < popIndexes.size(); j++) {
                res[popIndexes.get(j)][0] = leftNearestLess;
                res[popIndexes.get(j)][1] = -1;
            }
        }
        return res;
    }
}
