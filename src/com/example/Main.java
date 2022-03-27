package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        String[] ss = "D:\\zwtymj\\xccb\\ljj\\cqzlyaszjvlsjmkwoqijggmybr 645".split("\\\\");
        System.out.println(ss[ss.length - 1].substring(0, 160));



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