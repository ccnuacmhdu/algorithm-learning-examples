package com.example;

import sun.nio.cs.ext.MacHebrew;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            char[] cs = scanner.nextLine().toCharArray();
            StringBuffer sb = new StringBuffer();
            int flag = 0;
            int count = 1;
            for (int i = 0; i < cs.length; i++) {
                if (cs[i] == '\"') {
                    flag++;
                    continue;
                }
                if (cs[i] != ' ') {
                    sb.append(cs[i]);
                }
                if (cs[i] == ' ' && flag % 2 != 0) {
                    sb.append(cs[i]);
                }
                if (cs[i] == ' ' && flag % 2 == 0) {
                    sb.append("\n");
                    count++;
                }
            }
            System.out.println(count + "\n" + sb.toString());
        }
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