package com.example.twoPointers;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_3 {
    /**
     * 双指针，需要根据测试样例调试，难考虑全面
     *
     * 样例：au, pwwkew, tmmzuxt
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        int i = 0, j = 1;
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        map.put(cs[0], 0);
        int ans = 1;
        while (j < cs.length) {
            if(map.containsKey(cs[j]) && map.get(cs[j]) >= i) {
                ans = Math.max(ans, j - i);
                i = map.get(cs[j]) + 1;
            } else {
                ans = Math.max(ans, j - i + 1);
            }
            map.put(cs[j], j);
            j++;
        }
        return ans;
    }
}
