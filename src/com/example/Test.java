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
        if(nums.length < 2) {
            return nums.length;
        }
        Arrays.sort(nums);
        int tmpLen = 1;
        int maxLen = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]) {
                continue;
            }
            if(nums[i] == nums[i-1] + 1) {
                tmpLen++;
            } else {
                maxLen = Math.max(tmpLen, maxLen);
                tmpLen = 1;
            }
        }
        maxLen = Math.max(maxLen, tmpLen);
        return maxLen;
    }
}