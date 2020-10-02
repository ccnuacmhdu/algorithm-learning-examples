package com.example.programmerCodeInterviewGuide.chapter01;

import java.util.Scanner;
import java.util.Stack;

/**
 * 【由两个栈组成的队列】
 */
public class Code_01_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyQueue que = new MyQueue();
        int N = scanner.nextInt();
        for(int i = 0; i < N; i++) {
            String s = scanner.next();
            if(s.equals("add")) {
                Integer x = scanner.nextInt();
                que.add(x);
            } else if(s.equals("poll")) {
                que.poll();
            } else if(s.equals("peek")) {
                System.out.println(que.peek());
            }
        }
    }
}

class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void add(Integer i) {
        if(i != null) {
            stack1.add(i);
        }
    }

    public Integer poll() {
        if(stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if(!stack2.isEmpty()) {
            return stack2.pop();
        }
        return null;
    }

    public Integer peek() {
        if(!stack2.isEmpty()) {
            return stack2.peek();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        if(!stack2.isEmpty()) {
            return stack2.peek();
        }
        return null;
    }
}
