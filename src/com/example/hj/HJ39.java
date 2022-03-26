package com.example.hj;

import java.util.*;

/**
 * HJ39 判断两个IP是否属于同一子网
 */
public class HJ39 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String s = scanner.next();
            String ip1 = scanner.next();
            String ip2 = scanner.next();
            if(!(checkIp(ip1)) || !(checkIp(ip2)) || !checkMask(s)) {
                System.out.println(1);
                continue;
            }
            String ret1 = and(ip1, s);
            String ret2 = and(ip2, s);
            if(ret1.equals(ret2)) {
                System.out.println(0);
            } else {
                System.out.println(2);
            }
        }
    }

    private static String and(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        String[] ss1 = s1.split("\\.");
        String[] ss2 = s2.split("\\.");
        for(int i = 0; i < 4; i++) {
            int n1 = Integer.valueOf(ss1[i]);
            int n2 = Integer.valueOf(ss2[i]);
            sb.append(n1 & n2);
        }
        return sb.toString();
    }
    private static boolean checkS(String s) {
        String[] ss = s.split("\\.");
        for(String sub : ss) {
            for(int i = 0; i < sub.length(); i++) {
                if(!(sub.charAt(i) >= '0' && sub.charAt(i) <= '9')) {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean checkIp(String ip) {
        if(!checkS(ip)) return false;
        String[] ss = ip.split("\\.");
        if(ss.length != 4) return false;
        for(String s : ss) {
            int n = Integer.valueOf(s);
            if(n < 0 || n > 255) {
                return false;
            }
        }
        return true;
    }
    private static boolean checkMask(String mask) {
        if(!checkS(mask)) return false;
        String[] ss = mask.split("\\.");
        if(ss.length != 4) return false;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 4; i++) {
            int n = Integer.valueOf(ss[i]);
            if(n < 0 || n > 255) return false;
            String bi = Integer.toBinaryString(n);
            for(int j = 0; j < 8 - bi.length(); j++) {
                sb.append("0");
            }
            sb.append(bi);
        }
        String bs = sb.toString();
        boolean first0 = false;
        for(int i = 0; i < bs.length(); i++) {
            char c = bs.charAt(i);
            if(first0 && c == '1') {
                return false;
            }
            if(c == '0') {
                first0 = true;
            }
        }
        return true;
    }
}
