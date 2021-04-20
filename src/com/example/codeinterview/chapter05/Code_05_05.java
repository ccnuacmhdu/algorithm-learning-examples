package com.example.codeinterview.chapter05;

import java.util.Scanner;

public class Code_05_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s1 = scanner.next();
        String s2 = scanner.next();
        String[] ss = new String[n];
        for(int i = 0; i < n; i++) {
            ss[i] = scanner.next();
        }
        int minDis = getminDis(s1, s2, ss);
        System.out.println(minDis);
    }
    public static int getminDis(String s1, String s2, String[] ss) {
        int last1 = -1;
        int last2 = -1;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < ss.length; i++) {
            if(ss[i].equals(s1)) {
                last1 = i;
                min = last2 == -1 ? min : Math.min(min, i - last2);
            }
            if(ss[i].equals(s2)) {
                last2 = i;
                min = last1 == -1 ? min : Math.min(min, i - last1);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
