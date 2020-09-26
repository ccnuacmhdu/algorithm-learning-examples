package com.example.programmerCodeInterviewGuide.chapter01;

import java.util.Scanner;
import java.util.Stack;

/**
 * 【设计一个有 getMin 功能的栈】
 */
public class Code_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyStack2 myStack2 = new MyStack2();
        int N = scanner.nextInt();
        for(int i = 0; i < N; i++) {
            String s = scanner.next();
            if(s.equals("push")) {
                Integer x = scanner.nextInt();
                myStack2.push(x);
            } else if (s.equals("pop")) {
                myStack2.pop();
            } else if (s.equals("getMin")) {
                System.out.println(myStack2.getMin());
            }
        }
    }
}

/**
 * 【思路一】使用两个栈，一个栈存放数据，另一个栈存放当前最小值
 * 例如，5 2 7 3 依次入数据栈，存放当前最小值的栈依次入栈是 5 2 2 2
 * 此种方式，两个栈元素个数始终相同，最小值栈依次存放的就是当前最小值（MyStack1）
 *
 * 【思路二】使用两个栈，一个栈存放数据，另一个栈存放当前最小值
 * 例如，5 2 7 3 依次入数据栈，存放当前最小值的栈依次入栈是 5 2
 * 此种方式，两个栈元素个数不同，最小值栈存放的是当前最小值，当弹出时，
 * 只有最小值栈顶和数据栈顶相同时才对最小值栈进行pop（MyStack2）
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

class MyStack2 {

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack2() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(Integer i) {
        stackData.push(i);
        if(stackMin.isEmpty()) {
            stackMin.push(i);
        } else if(i <= stackMin.peek()){
            stackMin.push(i);
        }
    }

    public Integer pop() {
        if(stackData.isEmpty()) {
            return null;
        }
        if(stackData.peek() == stackMin.peek()) {
            stackMin.pop();
        }
        return stackData.pop();
    }

    public Integer getMin() {
        if(stackMin.isEmpty()) {
            return null;
        }
        return stackMin.peek();
    }
}