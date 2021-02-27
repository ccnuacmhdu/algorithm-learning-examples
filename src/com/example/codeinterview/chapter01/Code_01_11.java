package com.example.codeinterview.chapter01;

import java.util.Scanner;

/**
 * 答案是 2*n-3，具体推导过程见《程序员代码面试指南》左程云 第二版 电子版 49 页
 */
public class Code_01_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for(int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            int p = scanner.nextInt();
            int m = scanner.nextInt();
            if(n <= 1) {
                System.out.println(0);
            } else {
                int res = 2 * n - 3;
                System.out.println(res);
            }
        }
    }
}
