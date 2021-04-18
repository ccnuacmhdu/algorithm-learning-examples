package com.example.codeinterview.chapter05;

import java.util.Scanner;

public class Code_05_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String s1 = scanner.next();
        String s2 = scanner.next();
        boolean rotate = isRotate(s1, s2);
        String res = rotate ? "YES" : "NO";
        System.out.println(res);
    }

    // 自己的思路
    /*public static boolean isRotate(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        for(int i = 0; i < s1.length(); i++) {
            String sub10 = s1.substring(0, i);
            String sub11 = s1.substring(i);
            String sub20 = s2.substring(0, i);
            String sub21 = s2.substring(i);
            if((sub10.equals(sub20) && sub11.equals(sub21)) || (sub10.equals(sub21) && sub11.equals(sub20))) {
                return true;
            }
        }
        return false;
    }*/

    // 左神的思路
    public static boolean isRotate(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        String s22 = s2 + s2;
        return s22.indexOf(s1) != -1;
    }

}
