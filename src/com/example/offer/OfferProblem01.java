package com.example.offer;

import java.util.regex.Pattern;

/**
 * 【表示数值的字符串】
 *
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、
 * "-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 */
public class OfferProblem01 {

    /**
     * 【解法一：正则表达式】
     * 可以写几个完整的例子，照着写出正则表达式，如下，
     * -123.45e+67
     * +123.45E-67
     * e9 (error)
     *
     * 发现一个正则表达式太难写了，干脆写多个吧！
     * @param s
     * @return
     */
    public boolean isNumber_01(String s) {
        String pattern1 = "^[+|-]{0,1}[0-9]{1,}";
        String pattern2 = "^[+|-]{0,1}[0-9]{1,}";
        String patternNotScientificCounting = "";
        String pattern = "[+]?\\d*(\\.\\d+)?((\\d+(\\.\\d+)?)|(\\d*(\\.\\d+))[eE][\\+\\-]?\\d+)?";
        return Pattern.matches(pattern, s);
    }
}
