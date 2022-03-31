package com.example.hj;

import java.util.*;

public class HJ16 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int money = in.nextInt();
            int num = in.nextInt();
            // 记录主件、附件1、附件2的价格
            int[][] price = new int[num + 1][3];
            // 记录主件、附件1、附件2的重要性*价格
            int[][] imp = new int[num + 1][3];
            for(int i = 1; i <= num; i++) {
                int price0 = in.nextInt();
                int imp0 = in.nextInt();
                int par = in.nextInt();

                int imp1 = imp0 * price0;

                if(par == 0) {
                    price[i][0] = price0;
                    imp[i][0] = imp1;
                } else {
                    if(price[par][1] == 0) {
                        price[par][1] = price0;
                        imp[par][1] = imp1;
                    } else {
                        price[par][2] = price0;
                        imp[par][2] = imp1;
                    }
                }
            }
            int[][] dp = new int[num + 1][money + 1];
            // 由于前 0 种物品产生的收益必定是 0，所以初始化 dp[0][] 可以不做了
            int max = 0;
            for(int i = 1; i <= num; i++) {
                for (int j = 0; j <= money; j++) {
                    int a = price[i][0];
                    int a1 = imp[i][0];
                    int b = price[i][1];
                    int b1 = imp[i][1];
                    int c = price[i][2];
                    int c1 = imp[i][2];
                    int tMax = dp[i - 1][j];
                    if (j >= a) {
                        tMax = Math.max(dp[i - 1][j - a] + a1, tMax);
                    }
                    if (j >= a + b) {
                        tMax = Math.max(dp[i - 1][j - a - b] + a1 + b1, tMax);
                    }
                    if (j >= a + c) {
                        tMax = Math.max(dp[i - 1][j - a - c] + a1 + c1, tMax);
                    }
                    if (j >= a + b + c) {
                        tMax = Math.max(dp[i - 1][j - a - b - c] + a1 + b1 + c1, tMax);
                    }
                    dp[i][j] = tMax;
                }
                max = Math.max(max, dp[i][money]);
            }
            System.out.println(max);
        }
    }
}

