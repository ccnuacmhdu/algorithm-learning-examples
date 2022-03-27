package com.example.hj;

import java.util.*;

/**
 * HJ18 识别有效的IP地址和掩码并进行分类统计
 */
public class HJ18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cntA = 0, cntB = 0, cntC = 0, cntD = 0, cntE = 0;
        int cntErr = 0;
        int cntPrivate = 0;
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] ss = s.split("~");
            if(check0And127(ss[0])) continue;
            if(!checkIp(ss[0]) || !checkMask(ss[1])) {
                cntErr++;
            } else {
                String[] ss1 = ss[0].split("\\.");
                int ip1 = Integer.valueOf(ss1[0]);
                int ip2 = Integer.valueOf(ss1[1]);
                if(ip1 >= 1 && ip1 <= 126) {
                    if(ip1 == 10) {
                        cntPrivate++;
                    }
                    cntA++;
                } else if(ip1 >= 128 && ip1 <= 191) {
                    if(ip1 == 172 && ip2 >= 16 && ip2 <= 31) {
                        cntPrivate++;
                    }
                    cntB++;
                } else if(ip1 >= 192 && ip1 <= 223) {
                    if(ip1 == 192 && ip2 == 168) {
                        cntPrivate++;
                    }
                    cntC++;
                } else if(ip1 >= 224 && ip1 <= 239) {
                    cntD++;
                } else if(ip1 >= 240 && ip1 <= 255) {
                    cntE++;
                }
            }
        }
        System.out.printf("%d %d %d %d %d %d %d\n", cntA, cntB, cntC, cntD, cntE, cntErr, cntPrivate);
    }

    private static int compare(String s1, String s2) {
        String[] ss1 = s1.split("\\.");
        String[] ss2 = s2.split("\\.");
        for(int i = 0; i < 4; i++) {
            int x = Integer.valueOf(ss1[i]);
            int y = Integer.valueOf(ss2[i]);
            if(x < y) return -1;
            if(x > y) return 1;
        }
        return 0;
    }

    private static boolean check0And127(String s) {
        if(!check(s)) return false;
        if(s.startsWith("127") || s.startsWith("0")) return true;
        return false;
    }

    private static boolean check(String s) {
        String[] ss = s.split("\\.");
        if(ss.length != 4) return false;
        for(int i = 0; i < 4; i++) {
            if(ss[i].length() == 0) return false;
            for(int j = 0; j < ss[i].length(); j++) {
                char c = ss[i].charAt(j);
                if(!(c >= '0' && c <= '9')) {
                    return false;
                }
            }
            int num = Integer.valueOf(ss[i]);
            if(!(num >= 0 && num <= 255)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkIp(String ip) {
        if(!check(ip)) return false;
        String[] ss = ip.split("\\.");
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 4; i++) {
            int num = Integer.valueOf(ss[i]);
            if(!(num >= 0 && num <= 255)){
                return false;
            }
        }
        return true;
    }

    private static boolean checkMask(String mask) {
        if(!check(mask)) return false;
        if("0.0.0.0".equals(mask) || "255.255.255.255".equals(mask)) return false;
        String[] ss = mask.split("\\.");
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 4; i++) {
            String bi = Integer.toBinaryString(Integer.valueOf(ss[i]));
            int addCnt = 8 - bi.length();
            for(int j = 0; j < addCnt; j++) {
                sb.append("0");
            }
            sb.append(bi);
        }
        char[] cs = sb.toString().toCharArray();
        boolean has0 = false;
        for(int i = 0; i < cs.length; i++) {
            if(has0 && cs[i] == '1') return false;
            if(cs[i] == '0') {
                has0 = true;
            }
        }
        return true;
    }

}
