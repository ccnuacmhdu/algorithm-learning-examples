package com.example.codeinterview.chapter05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Code_05_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String s1 = scanner.next();
        String s2 = scanner.next();
        boolean deformation = isDeformation(s1, s2);
        System.out.println(deformation);
    }

    // 法一：排序即可
    /*public static boolean isDeformation(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        char[] chs1 = s1.trim().toCharArray();
        char[] chs2 = s2.trim().toCharArray();
        Arrays.sort(chs1);
        Arrays.sort(chs2);
        return Arrays.equals(chs1, chs2);
    }*/

    // 法二：模拟题意
    public static boolean isDeformation(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }
        for(int i = 0; i < s2.length(); i++) {
            map.put(s2.charAt(i), map.getOrDefault(s2.charAt(i), 0) - 1);
            if(map.get(s2.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }
}
