package com.example.codeInterview.chapter05;

import java.util.Scanner;

public class Code_05_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String res = getString(s);
        System.out.println(res);
    }
    public static String getString(String s) {
        int[] times = new int[26];
        for(int i = 0; i < s.length(); i++) {
            times[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        boolean[] vis = new boolean[26];
        for(int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - 'a';
            times[num]--;
            if(vis[num]) {
                continue;
            }
            // sb末尾字符后面还会访问到且大于当前字符，就得被干掉
            while (sb.length() > 0 && times[sb.charAt(sb.length() - 1) - 'a'] > 0
                    && sb.charAt(sb.length() - 1) > s.charAt(i)) {
                vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(s.charAt(i));
            vis[num] = true;
        }
        return sb.toString();
    }
}
