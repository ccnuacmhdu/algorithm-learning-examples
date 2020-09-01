package com.example.greedy;

import java.util.Arrays;

/**
 * 【字符串拼接问题】
 * 给定一个字符串类型的数组 strs，找到一种拼接方式，使得把所有字符串拼起来之后形成的字符串具有最小的字典序。
 *
 * 【贪心策略 1】
 * 把所有单词按照字典序排序，然后直接连起来，这种策略是错误的。比如字符串"da"和字符串"d"，若按照这种策略拼接结果是"dda"，正确答案是"dad"。
 * 【贪心策略 2】 str1+str2 的字典序小于 str2+str1，那么拼接策略是 str1+str2，否则拼接策略是 str2+str1
 */
public class GreedyExample01 {
    public static String minimumLexicographic(String[] strs) {
        if(null == strs || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        StringBuffer sb = new StringBuffer();
        for(String s: strs) {
            sb.append(s);
        }
        return new String(sb);
    }

    public static void main(String[] args) {
        String[] strs = {"da", "d"};
        System.out.println(minimumLexicographic(strs));
    }
}
