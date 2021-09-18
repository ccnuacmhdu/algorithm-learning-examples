package com.example;

import java.awt.datatransfer.FlavorListener;
import java.sql.Struct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        String[] strs = new String[]{"bdddddddddd","bbbbbbbbbbc"};
        Solution solution = new Solution();

    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
     this.val = val;
     this.left = left;
     this.right = right;
    }
}

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int maxLen = 0;
        for(int i: set) {
            if(!set.contains(i-1)) {
                int num = i+1;
                int tmpLen = 1;
                while (set.contains(num)) {
                    tmpLen++;
                    num++;
                }
                maxLen = Math.max(maxLen, tmpLen);
            }
        }
        return maxLen;
    }
}