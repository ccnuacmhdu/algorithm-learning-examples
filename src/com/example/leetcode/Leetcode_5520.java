package com.example.leetcode;

import java.util.ArrayList;

/**
 * 【拆分字符串使唯一子字符串的数目最大】
 */
public class Leetcode_5520 {
    public int maxUniqueSplit(String s) {
        int[] res = new int[1];
        f(s, 0, new ArrayList<String>(), res);
        return res[0];
    }
    private boolean f(String s, int st, ArrayList<String> list, int[] res) {
        if(st == s.length()) {
            return true;
        }
        for(int i = st; i < s.length(); i++) {
            String tmp = s.substring(st, i+1);
            if(list.contains(tmp)) {
                continue;
            }
            list.add(tmp);
            if(f(s, i+1, list, res)) {
                res[0] = Math.max(res[0], list.size());
            }
            list.remove(list.size()-1);
        }
        return false;
    }
}
