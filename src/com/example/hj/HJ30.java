package com.example.hj;

import java.util.*;

/**
 * HJ30 字符串合并处理
 */
public class HJ30 {

    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static char[] biRHex = getBiR();
    private static Map<Character, Character> map = getMap();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String s = scanner.nextLine();
            String s2 = s.replace(" ", "");
            char[] cs = s2.toCharArray();
            StringBuffer oddSb = new StringBuffer();
            StringBuffer evenSb = new StringBuffer();
            for(int i = 0; i < cs.length; i++) {
                if(i % 2 == 0) {
                    evenSb.append(cs[i]);
                } else {
                    oddSb.append(cs[i]);
                }
            }
            char[] oddCs = oddSb.toString().toCharArray();
            char[] evenCs = evenSb.toString().toCharArray();
            Arrays.sort(oddCs);
            Arrays.sort(evenCs);
            StringBuffer sortSb = new StringBuffer();
            int evenId = 0, oddId = 0;
            while(evenId < evenCs.length || oddId < oddCs.length) {
                if(evenId < evenCs.length) {
                    sortSb.append(evenCs[evenId++]);
                }
                if(oddId < oddCs.length) {
                    sortSb.append(oddCs[oddId++]);
                }
            }
            char[] sortCs = sortSb.toString().toCharArray();
            StringBuffer retSb = new StringBuffer();
            for(char c : sortCs) {
                if((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')) {
                    retSb.append(map.get(c));
                } else {
                    retSb.append(c);
                }
            }
            System.out.println(retSb.toString());
        }
    }

    private static int biR(int n) {
        String bs = Integer.toBinaryString(n);
        int addNum = 4 - bs.length();
        StringBuffer ret = new StringBuffer();
        for(int i = 0; i < addNum; i++) {
            ret.append('0');
        }
        ret.append(bs);
        char[] cs = ret.reverse().toString().toCharArray();
        int sum = 0;
        for(char c : cs) {
            sum = sum * 2 + (c - '0');
        }
        return sum;
    }

    private static char[] getBiR() {
        char[] cs = new char[16];
        for(int i = 0; i < 16; i++) {
            int n = biR(i);
            cs[i] = hex[n];
        }
        return cs;
    }

    private static Map<Character, Character> getMap() {
        Map<Character, Character> map = new HashMap<>();
        for(char i = '0'; i <= '9'; i++) {
            map.put(i, biRHex[i - '0']);
        }
        for(char i = 'a'; i <= 'f'; i++) {
            map.put(i, biRHex[i - 'a' + 10]);
        }
        for(char i = 'A'; i <= 'F'; i++) {
            map.put(i, biRHex[i - 'A' + 10]);
        }
        return map;
    }

}
