package com.example.codeInterview.chapter01;

import java.util.Scanner;
import java.util.Stack;

public class Code_01_05 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            stack.push(x);
        }
        sortStackByStack(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    /**
     * （本人所写）每次求出最小的，就完事了
     *
     * @param stack
     */
    public static void sortStackByStack(Stack<Integer> stack) {
        if(stack == null || stack.size() == 0) {
            return;
        }
        Stack<Integer> help = new Stack<>();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            int min = stack.peek();
            for(int j = i; j < size; j++) {
                int peek = stack.pop();
                min = min > peek ? peek : min;
                help.push(peek);
            }
            stack.push(min);
            boolean first = true;
            for(int j = i; j < size; j++) {
                int peek = help.pop();
                if(first && peek == min) {
                    first = false;
                    continue;
                }
                stack.push(peek);
            }
        }
    }

    /**
     * （左程云所写）把 help 栈从栈顶到栈底按照从小到大排序，再倒入 stack
     *
     * @param stack
     */
//    public static void sortStackByStack(Stack<Integer> stack) {
//        Stack<Integer> help = new Stack<>();
//        while (!stack.isEmpty()) {
//            int cur = stack.pop();
//            // 大，那就把大的往下放
//            while (!help.isEmpty() && cur > help.peek()) {
//                stack.push(help.pop());
//            }
//            // 小，就直接放进去
//            help.push(cur);
//        }
//        while (!help.isEmpty()) {
//            stack.push(help.pop());
//        }
//    }
}
