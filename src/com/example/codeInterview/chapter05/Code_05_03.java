package com.example.codeInterview.chapter05;

// [leetcode] 剑指 Offer 67. 把字符串转换成整数
public class Code_05_03 {

//    public static void main(String[] args) {
//        String s = "-2147483648";
//        int res = strToInt(s);
//        System.out.println(res);
//    }

    public int strToInt(String str) {
        if(str == null || str.trim().length() == 0) {
            return 0;
        }
        String s = str.trim();
        String sign = (s.charAt(0) == '+' || s.charAt(0) == '-') ? String.valueOf(s.charAt(0)) : "";
        int i = ("+".equals(sign) || "-".equals(sign)) ? 1 : 0;
        if(i < s.length() && !(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
            return 0;
        }
        long v = 0L;
        for(; i < s.length(); i++) {
            if((s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
               v = v * 10 + (s.charAt(i) - '0');
               if("-".equals(sign) && v >= 1L + Integer.MAX_VALUE) {
                   return Integer.MIN_VALUE;
               }
               if(("+".equals(sign) || "".equals(sign)) && v >= Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
               }
            } else {
                break;
            }
        }
        return "-".equals(sign) ? (int)-v : (int)v;
    }
}
