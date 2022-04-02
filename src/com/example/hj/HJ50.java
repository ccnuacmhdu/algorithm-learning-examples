package com.example.hj;

import java.util.*;

/**
 * HJ50 四则运算
 *
 * tips: 中缀表达式运算，中缀转后缀再计算
 */
public class HJ50 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            str = str.replace("[", "(")
                    .replace("{", "(")
                    .replace("]", ")")
                    .replace("}", ")");
            System.out.println(calSuffix(infix2Suffix(str)));
        }
    }

    // 中缀表达式 转 后缀表达式
    public static List<String> infix2Suffix(String str) {
        List<String> result = new ArrayList<>();
        Stack<Character> operatorStack = new Stack<>();
        boolean flag = true;
        String tmp = "";
        for (char c : str.toCharArray()) {
            // 负数开头处理（补零）
            if (flag && c == '-') {
                result.add("0");
            }
            flag = false;
            // 多位数处理
            if (c >= '0' && c <= '9') {
                tmp += c;
                continue;
            }
            // 数字入栈（集合）
            if (tmp.length() > 0) {
                result.add(tmp);
                tmp = "";
            }
            // 符号入栈（集合）
            if (operatorStack.empty() || operatorStack.peek() == '(') {
                operatorStack.push(c);
            } else if (c == '(') {
                operatorStack.push(c);
                flag = true;
            } else if (c == ')'){
                while (operatorStack.peek() != '(') {
                    result.add(operatorStack.pop() + "");
                }
                operatorStack.pop();
            } else {
                while (!operatorStack.empty()
                        && operatorStack.peek() != '('
                        && getPriority(c) <= getPriority(operatorStack.peek())) {
                    result.add(operatorStack.pop() + "");
                }
                operatorStack.push(c);
            }
        }
        // 后续处理
        if (tmp.length() > 0) {
            result.add(tmp);
        }
        while (!operatorStack.empty()) {
            result.add(operatorStack.pop() + "");
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
    public static int calSuffix(List<String> list) {
        Stack<Integer> numStack = new Stack<>();
        for (String item : list) {
            switch (item) {
                case "+" :
                case "-" :
                case "*" :
                case "/" :
                    int tmp = cal(numStack.pop(), numStack.pop(), item);
                    numStack.push(tmp);
                    break;
                default :
                    numStack.push(Integer.parseInt(item));
            }
        }
        return numStack.peek();
    }

    // 计算加 减 乘 除
    public static int cal(int num1, int num2, String operator) {
        switch (operator) {
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
