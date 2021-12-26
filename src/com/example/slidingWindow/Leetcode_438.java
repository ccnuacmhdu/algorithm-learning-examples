package com.example.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()) return res;
        char[] pc = p.toCharArray();
        char[] sc = s.toCharArray();
        int[] pi = new int[26];
        int[] si = new int[26];
        for(int i = 0; i < pc.length; i++) {
            ++pi[pc[i] - 'a'];
            ++si[sc[i] - 'a'];
        }
        if(Arrays.equals(pi, si)) {
            res.add(0);
        }
        int size = sc.length - pc.length; // i + pc.length - 1 < sc.length，且第一个窗口已经比较过
        for(int i = 0; i < size; i++) {
            --si[sc[i] - 'a'];
            ++si[sc[i + pc.length] - 'a'];
            if(Arrays.equals(si, pi)) {
                res.add(i + 1);
            }
        }
        return res;
    }

    // 暴力法
//    public List<Integer> findAnagrams(String s, String p) {
//        List<Integer> res = new ArrayList<>();
//        if(s.length() < p.length()) return res;
//        char[] pc = p.toCharArray();
//        Arrays.sort(pc);
//        for(int i = 0; i + p.length() - 1 < s.length(); i++) {
//            String t = s.substring(i, i + p.length());
//            char[] tc = t.toCharArray();
//            Arrays.sort(tc);
//            if(Arrays.equals(pc, tc)) {
//                res.add(i);
//            }
//        }
//        return res;
//    }
}
