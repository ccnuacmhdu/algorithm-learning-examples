package com.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            String s = in.nextLine();
            String ret = check(s) ? "OK" : "NG";
            System.out.println(ret);
        }
    }

    private static boolean check(String s) {
        if(s == null || s.length() <= 8) return false;

        char[] cs = s.toCharArray();
        int upper = 0;
        int lower = 0;
        int number = 0;
        int other = 0;
        for(char c : cs) {
            if(c >= '0' && c <= '9') number = 1;
            else if(c >= 'a' && c <= 'z') lower = 1;
            else if(c >= 'A' && c <= 'Z') upper = 1;
            else other = 1;
        }
        if(upper + lower + number + other < 3) return false;

        for(int i = 0; i < s.length() - 6; i++) {
            String s1 = s.substring(i, i + 3);
            String s2 = s.substring(i + 3);
            if(s2.contains(s1)) return false;
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