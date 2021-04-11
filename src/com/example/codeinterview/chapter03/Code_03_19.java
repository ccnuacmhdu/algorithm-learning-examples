package com.example.codeinterview.chapter03;

import java.util.Scanner;

public class Code_03_19 {
    private static int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] num = prepare();
        System.out.println(num[n]);
    }
    public static long[] prepare() {
        int n = 10000;
        long[] num = new long[n+1];
        num[0] = 1;
        // 枚举所有情况，有i个节点，分别以j为头的各种情况之和就是num[i]
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                num[i] = (num[i] % MOD + ((num[j-1] % MOD) * (num[i-j] % MOD)) % MOD) % MOD;
            }
        }
        return num;
    }
}
