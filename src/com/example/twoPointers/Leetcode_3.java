package com.example.twoPointers;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_3 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int i = 0, j = 0;

        Map<Character, Integer> map = new HashMap<>();

        int ans = 0;
        while(i < n && j < n) {
            char c = s.charAt(j);
            if((!map.containsKey(c)) || (map.containsKey(c) && map.get(c) < i)) {
                map.put(c, j);
            } else {
                ans = Math.max(ans, j - i);
                i = map.get(c) + 1;
                map.put(c, j);
            }
            j++;
        }
        ans = Math.max(ans, j - i);
        return ans;
    }
}
