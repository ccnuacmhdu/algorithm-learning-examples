package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            // 初始化，将"{}""[]"替换为"()"
            str = str.replace("[", "(")
                    .replace("{", "(")
                    .replace("]", ")")
                    .replace("}", ")");
            System.out.println(culSuffix(infixToSuffix(str)));
        }
    }

    // 中缀表达式 转 后缀表达式
    public static List<String> infixToSuffix(String str) {
        List<String> result = new ArrayList<>();
        Stack<Character> operateStack = new Stack<>();
        boolean flag = true;
        String temp = "";
        for (char c : str.toCharArray()) {
            // 负数开头处理（补零）
            if (flag && c == '-') {
                result.add("0");
            }
            flag = false;
            // 多位数处理
            if (c >= '0' && c <= '9') {
                temp += c;
                continue;
            }
            // 数字入栈（集合）
            if (temp.length() > 0) {
                result.add(temp);
                temp = "";
            }
            // 符号入栈（集合）
            if (operateStack.empty() || operateStack.peek() == '(') {
                operateStack.push(c);
            } else if (c == '(') {
                operateStack.push(c);
                flag = true;
            } else if (c == ')'){
                while (operateStack.peek() != '(') {
                    result.add(operateStack.pop() + "");
                }
                operateStack.pop();
            } else {
                while (!operateStack.empty()
                        && operateStack.peek() != '('
                        && getPriority(c) <= getPriority(operateStack.peek())) {
                    result.add(operateStack.pop() + "");
                }
                operateStack.push(c);
            }
        }
        // 后续处理
        if (temp.length() > 0) {
            result.add(temp);
        }
        while (!operateStack.empty()) {
            result.add(operateStack.pop() + "");
        }
        return result;
    }

    // 获取操作符优先级
    public static int getPriority(char c) {
        if (c == '+' || c == '-') {
            return 1;
        }
        if (c == '*' || c == '/') {
            return 2;
        }
        throw new RuntimeException("异常操作符！");
    }

    // 计算后缀表达式
    public static int culSuffix(List<String> list) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> operateStack = new Stack<>();
        Integer temp = null;
        for (String item : list) {
            switch (item) {
                case "+" :
                case "-" :
                case "*" :
                case "/" :
                    temp = cul(numStack.pop(), numStack.pop(), item);
                    numStack.push(temp);
                    break;
                default :
                    numStack.push(Integer.parseInt(item));
            }
        }
        return numStack.peek();
    }

    // 计算加 减 乘 除
    public static int cul(int num1, int num2, String operate) {
        switch (operate) {
            case "+" :
                return num2 + num1;
            case "-" :
                return num2 - num1;
            case "*" :
                return num2 * num1;
            case "/" :
                return num2 / num1;
        }
        throw new RuntimeException("异常数据，计算失败！");
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}