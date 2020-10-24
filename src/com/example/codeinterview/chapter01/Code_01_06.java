package com.example.codeinterview.chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 汉诺塔问题，递归实现
 */
public class Code_01_06 {
    public static void main(String[] args) {
//        List<Integer> A = Arrays.asList(2, 1, 0); // 这个坑，返回的 ArrayList 是个 Arrays 的内部类，没有 add 等方法。。
        List<Integer> A = new ArrayList<>();
        A.add(2);
        A.add(1);
        A.add(0);
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        hanota(A, B, C);
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);

        hanota(4, 'A', 'B', 'C');

        hanota(2);
    }

    /**
     * Leetcode 面试题 08.06. 汉诺塔问题
     *
     * @param A
     * @param B
     * @param C
     */
    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanota(A, B, C, A.size());
    }
    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C, int remain) {
        if(remain == 1) {
            C.add(A.remove(A.size()-1));
            return;
        }
        hanota(A, C, B, remain-1);
        C.add(A.remove(A.size()-1));
        hanota(B, A, C, remain-1);
    }

    /**
     * 牛客网 用栈来求解汉诺塔问题（下面实现不用栈，栈太复杂）
     *
     * 题目修改了，不允许直接 A->C 或 C->A，必须经 B 中转。其实是经典汉诺塔问题的简单改编。
     * 1）把上面 n-1 个进行 A->B, B->C 操作
     * 2）把第 n 个进行 A->B 操作
     * 3）把上面 n-1 个进行 C->B, B->A 操作
     * 4）把第 n 个进行 B->C 操作
     * 5）把上面 n-1 个进行 A->B, B->C 操作
     *
     * @param n
     */
    public static void hanota(int n) {
        int[] num = {0};
        hanota(n, "left", "mid", "right", num);
        System.out.println("It will move " + num[0] + " steps.");
    }
    public static void hanota(int n, String left, String mid, String right, int[] num) {
        if(n == 1) {
            System.out.println("Move " + n + " from " + left + " to " + mid);
            System.out.println("Move " + n + " from " + mid + " to " + right);
            num[0] += 2;
            return;
        }
        hanota(n-1, left, mid, right, num);
        System.out.println("Move " + n + " from " + left + " to " + mid);
        num[0]++;
        hanota(n-1, right, mid, left, num);
        System.out.println("Move " + n + " from " + mid + " to " + right);
        num[0]++;
        hanota(n-1, left, mid, right, num);
    }

    /**
     * 经典汉诺塔问题
     * 1）把上面 n-1 个进行 A->B 操作（借助 C）
     * 2）把第 n 个进行 A->C 操作
     * 3）把上面 n-1 个进行 B->C 操作（借助A）
     *
     * @param n
     * @param a
     * @param b
     * @param c
     */
    public static void hanota(int n, char a, char b, char c) {
        if(n == 1) {
            System.out.println(a + "->" + c);
            return;
        }
        hanota(n-1, a, c, b);
        System.out.println(a + "->" + c);
        hanota(n-1, b, a, c);
    }
}
