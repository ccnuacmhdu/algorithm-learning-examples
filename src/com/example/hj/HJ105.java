package com.example.hj;

import java.util.*;

/**
 * HJ105 记负均正II
 */
public class HJ105 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        int negN = 0;
        int posSum = 0;
        double posAvg = 0.0;
        while(scanner.hasNext()) {
            int x = scanner.nextInt();
            n++;
            if(x < 0) {
                negN++;
            } else {
                posSum += x;
            }
        }
        if(negN < n) {
            posAvg = posSum * 1.0 / (n - negN);
        }
        System.out.println(negN);
        System.out.printf("%.1f\n", posAvg);
    }
}
