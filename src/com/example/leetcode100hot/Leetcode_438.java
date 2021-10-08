package com.example.leetcode100hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int[] charCount = new int[26];
        char[] sarray = s.toCharArray();
        char[] parray = p.toCharArray();
        int l1 = s.length();
        int l2 = p.length();

        for(char ch : parray)
            charCount[ch - 'a']++;

        int l = 0, r = 0;

        while(l <= l1 - l2)
        {
            while(r < l + l2 && charCount[sarray[r] - 'a'] >= 1)
            {
                charCount[sarray[r] - 'a']--;
                r++;
            }

            if(r == l + l2)
                list.add(l);

            charCount[sarray[l] - 'a']++;
            l++;
        }

        return list;
    }
}
