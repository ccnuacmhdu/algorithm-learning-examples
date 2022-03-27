package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> ipList = new ArrayList<>();
        List<String> maskList = new ArrayList<>();
        int cntA = 0, cntB = 0, cntC = 0, cntD = 0, cntE = 0;
        int cntErr = 0;
        int cntPrivate = 0;
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] ss = s.split("~");
            ipList.add(ss[0]);
            maskList.add(ss[1]);
            if(!check(ss[0])) {
                cntErr++;
            } else {
                if(ss[0].compareTo("1.0.0.0") >= 0 && ss[0].compareTo("126.255.255.255") <= 0) {
                    if(ss[0].compareTo("10.0.0.0") >= 0 && ss[0].compareTo("10.255.255.255") <= 0) {
                        cntPrivate++;
                    }
                    cntA++;
                } else if(ss[0].compareTo("128.0.0.0") >= 0 && ss[0].compareTo("191.255.255.255") <= 0) {
                    if(ss[0].compareTo("172.16.0.0") >= 0 && ss[0].compareTo("172.31.255.255") <= 0) {
                        cntPrivate++;
                    }
                    cntB++;
                } else if(ss[0].compareTo("192.0.0.0") >= 0 && ss[0].compareTo("223.255.255.255") <= 0) {
                    if(ss[0].compareTo("192.168.0.0") >= 0 && ss[0].compareTo("192.168.255.255") <= 0) {
                        cntPrivate++;
                    }
                    cntC++;
                } else if(ss[0].compareTo("224.0.0.0") >= 0 && ss[0].compareTo("239.255.255.255") <= 0) {
                    cntD++;
                } else if(ss[0].compareTo("240.0.0.0") >= 0 && ss[0].compareTo("255.255.255.255") <= 0) {
                    cntE++;
                }
            }
            if(!checkMask(ss[1])) {
                cntErr++;
            }
        }
        System.out.printf("%d %d %d %d %d %d %d\n", cntA, cntB, cntC, cntD, cntE, cntErr, cntPrivate);
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

    private static boolean checkMask(String mask) {
        if(!check(mask)) return false;
        if("0.0.0.0".equals(mask) || "1.1.1.1".equals(mask)) return false;
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


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}