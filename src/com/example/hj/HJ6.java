package com.example.hj;

import java.util.Scanner;

/**
 * HJ6 质数因子
 */
public class HJ6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int n = in.nextInt();
            for(int i = 2; i <= Math.sqrt(n); i++) {
                if(n == 1) break;
                while (n % i == 0) {
                    System.out.print(i + " ");
                    n /= i;
                }
            }
            if(n != 1) {
                System.out.print(n + " ");
            }
        }
    }
}
