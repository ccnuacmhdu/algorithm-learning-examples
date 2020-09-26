package com.example.programmerCodeInterviewGuide.chapter01;

import java.util.Scanner;
import java.util.Stack;

/**
 * 【设计一个有 getMin 功能的栈】
 */
public class Code_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyStack1 myStack1 = new MyStack1();
        int N = scanner.nextInt();
        for(int i = 0; i < N; i++) {
            String s = scanner.next();
            if(s.equals("push")) {
                Integer x = scanner.nextInt();
                myStack1.push(x);
            } else if (s.equals("pop")) {
                myStack1.pop();
            } else if (s.equals("getMin")) {
                System.out.println(myStack1.getMin());
            }
        }
    }
}

/**
 * 【思路】使用两个栈，一个栈存放数据，另一个栈存放当前最小值
 * 例如，5 2 7 3 依次入数据栈，存放当前最小值的栈依次入栈是 3 3 2 2
 */
class MyStack1 {

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack1() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(Integer i) {
        stackData.push(i);
        if(stackMin.isEmpty()) {
            stackMin.push(i);
        } else {
            int min = Math.min(i, stackMin.peek());
            stackMin.push(min);
        }
    }

    public Integer pop() {
        if(stackData.isEmpty()) {
            return null;
        }
        stackMin.pop();
        return stackData.pop();
    }

    public Integer getMin() {
        if(stackMin.isEmpty()) {
            return null;
        }
        return stackMin.peek();
    }
}