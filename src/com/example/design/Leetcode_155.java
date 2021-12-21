package com.example.design;

import java.util.Stack;

public class Leetcode_155 {
    class MinStack {

        private Stack<Integer> minStack = new Stack<>();
        private Stack<Integer> dataStack = new Stack<>();

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int val) {
            dataStack.push(val);
            if(minStack.size() == 0) {
                minStack.push(val);
            } else {
                int min = Math.min(minStack.peek(), val);
                minStack.push(min);
            }
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
