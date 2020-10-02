package com.example.programmerCodeInterviewGuide.chapter01;

import java.util.Scanner;
import java.util.Stack;

public class Code_01_03 {
    /**
     * 把一个栈逆序，弹出栈底返回
     *
     * @param stack
     * @return
     */
    private static Integer reverseAndGetStackBottom(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return null;
        }
        if(stack.size() == 1) {
            return stack.pop();
        }
        int peek = stack.pop();
        int ret = reverseAndGetStackBottom(stack);
        stack.push(peek);
        return ret;
    }

    public static void reverseStack(Stack<Integer> stack) {
        if(stack == null || stack.isEmpty()) {
            return;
        }
        int bottom  = reverseAndGetStackBottom(stack);
        reverseStack(stack);
        stack.push(bottom);
        // 上面三行递归代码也可用下面代码替换
//        int n = stack.size();
//        while (n > 0) {
//            int bottom = reverseAndGetStackBottom(stack);
//            stack.push(bottom);
//            n--;
//        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int N = scanner.nextInt();
        for(int i = 0; i < N; i++) {
            int tmp = scanner.nextInt();
            stack.push(tmp);
        }
        reverseStack(stack);
        reverseStack(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}
