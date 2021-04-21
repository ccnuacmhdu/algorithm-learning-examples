package com.example.codeinterview.chapter05;

import java.util.HashSet;
import java.util.Set;

// [leetcode] 3. 无重复字符的最长子串
public class Code_05_07 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int r = -1; // 右边界
        int ans = 0;
        // 思想：必须以s.charAt(i)开头的最长子字符串
        for(int i = 0; i < s.length(); i++) {
            if(i != 0) {
                // 左边界右进一步
                set.remove(s.charAt(i-1));
            }
            while (r+1 < s.length() && !set.contains(s.charAt(r+1))) {
                set.add(s.charAt(r+1));
                r++;
            }
            ans = Math.max(ans, r - i + 1);
        }
        return ans;
    }
}
