package com.example.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_17 {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) {
            return res;
        }
        String[] dic = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        process(dic, digits,0, new StringBuffer(), res);
        return res;
    }
    private void process(String[] dic, String s, int st, StringBuffer sb, List<String> res) {
        if(st == s.length()) {
            res.add(sb.toString());
            return;
        }
        int idx = s.charAt(st) - '0';
        char[] cs = dic[idx].toCharArray();
        for(int i = 0; i < cs.length; i++) {
            sb.append(cs[i]);
            process(dic, s, st + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
