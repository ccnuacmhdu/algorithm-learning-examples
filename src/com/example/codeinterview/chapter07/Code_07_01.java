package com.example.codeinterview.chapter07;

import java.util.Scanner;

public class Code_07_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        swap1(a, b);
    }
    // 法一
    public static void swap1(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
        System.out.println(a + " " + b);
    }
    // 法二
    public static void swap2(int a, int b) {
        a = a - b;
        b = a + b;
        a = b - a;
        System.out.println(a + " " + b);
    }
    // 法三
    public static void swap3(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + " " + b);
    }
    // 法三的特殊bug（若i=j，导致a[i]=0）
    public static void swap3bug(int[] a, int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
        System.out.println(a[i] + " " + a[j]);
    }

}
