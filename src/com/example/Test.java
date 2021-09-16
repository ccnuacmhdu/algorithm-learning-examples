package com.example;

import java.awt.datatransfer.FlavorListener;
import java.sql.Struct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        String[] strs = new String[]{"bdddddddddd","bbbbbbbbbbc"};
        Solution solution = new Solution();
        List<List<String>> lists = solution.groupAnagrams(strs);
        for(List<String> list: lists) {
            for(String s: list) {
                System.out.print(s + "\t");
            }
            System.out.println();
        }
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
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++) {
            String k = process(strs[i]);
            List<String> list = map.getOrDefault(k, new ArrayList<>());
            list.add(strs[i]);
            map.put(k, list);
        }
        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
    private String process(String s) {
        int[] cnt = new int[26];
        for(int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++) {
            sb.append(cnt[i]).append("-");
        }
        return sb.toString();
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            Arrays.sort(cs);
            String k = new String(cs);
            List<String> list = map.getOrDefault(k, new ArrayList<>());
            list.add(strs[i]);
            map.put(k, list);
        }
        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
}