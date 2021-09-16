package com.example.leetcode100hot;

import java.util.*;

public class Leetcode_49 {
    // 方法一：计数
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

    // 方法二：排序
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
