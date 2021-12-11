package com.example.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Leetcode_22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        process(n*2, new StringBuffer(), res);
        return res;
    }
    private void process(int n, StringBuffer sb, List<String> res) {
        if(n == 0) {
            if(isValid(sb)) {
                res.add(sb.toString());
            }
            return;
        }
        process(n - 1, sb.append('('), res);
        sb.deleteCharAt(sb.length() - 1);
        process(n - 1, sb.append(')'), res);
        sb.deleteCharAt(sb.length() - 1);
    }
    private boolean isValid(StringBuffer sb) {
        Stack<Character> stack = new Stack<>();
        char[] cs = sb.toString().toCharArray();
        for(int i = 0; i < cs.length; i++) {
            if(cs[i] == '(') {
                stack.push(cs[i]);
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if(pop != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
