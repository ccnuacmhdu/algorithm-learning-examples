package com.example.offer;

import java.util.HashMap;
import java.util.Map;
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
     * 其实应该清楚表示数值的明确定义，同时正则基于自动机，所以应先参看 README.md 中的自动机图解。
     * 注意该自动机图中处理了字符串两端空格，可以把空格去掉。几个例子，-123.45e+67、+123.45E-67、
     * e9 (error)，写较完整的例子利于写状态机。
     *
     * 小数点不能左右两边都没数，至少一边得有数，本解法就是基于此。如果一个正则表达式不易写，也可分
     * 类分别写出正则，分别判断是否匹配即可。
     * @param s
     * @return
     */
    public boolean isNumber_01(String s) {
        s = s.trim();
        String pattern = "^[+|-]?((\\d+\\.?\\d*)|(\\d*\\.\\d+))([E|e][+|-]?\\d+)?$";
        return Pattern.matches(pattern, s);
    }

    /**
     * 【解法二：自动机】
     * 1. 先参看 README.md 中的自动机图解，注意下面解法不考虑开头结尾空格，先去掉之。
     * 2. 简单粗暴地根据自动机，把每个状态和其能够到达的所有状态构成 Map。
     * 3. 遍历字符串中所有字符，根据状态转移 Map 走一遍，看最终的状态能否达到终态。
     * @param s
     * @return
     */
    public boolean isNumber_02(String s) {
        s = s.trim();
        Map<State, Map<CharType, State>> transfer = new HashMap<>();

        Map<CharType, State> initialMap = new HashMap();
        initialMap.put(CharType.CHAR_PLUS_MINUS_SIGN, State.STATE_INTEGER_PLUS_MINUS_SIGN);
        initialMap.put(CharType.CHAR_INTEGER_NUMBER, State.STATE_INTEGER_NUMBER);
        initialMap.put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_LEFT);
        // STATE_INITIAL -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_INITIAL, initialMap);

        Map<CharType, State> integerPlusMinusSignMap = new HashMap();
        integerPlusMinusSignMap.put(CharType.CHAR_INTEGER_NUMBER, State.STATE_INTEGER_NUMBER);
        integerPlusMinusSignMap.put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_LEFT);
        // STATE_INTEGER_PLUS_MINUS_SIGN -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_INTEGER_PLUS_MINUS_SIGN, integerPlusMinusSignMap);

        Map<CharType, State> integerNumberMap = new HashMap();
        integerNumberMap.put(CharType.CHAR_INTEGER_NUMBER, State.STATE_INTEGER_NUMBER);
        integerNumberMap.put(CharType.CHAR_POINT, State.STATE_POINT_WITH_LEFT);
        integerNumberMap.put(CharType.CHAR_EXPONENT_E, State.STATE_EXPONENT_E);
        integerNumberMap.put(CharType.CHAR_END_FLAG, State.STATE_END);
        // STATE_INTEGER_NUMBER -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_INTEGER_NUMBER, integerNumberMap);

        Map<CharType, State> pointWithLeftMap = new HashMap();
        pointWithLeftMap.put(CharType.CHAR_INTEGER_NUMBER, State.STATE_POINT_NUMBER);
        pointWithLeftMap.put(CharType.CHAR_EXPONENT_E, State.STATE_EXPONENT_E);
        pointWithLeftMap.put(CharType.CHAR_END_FLAG, State.STATE_END);
        // STATE_POINT_WITH_LEFT -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_POINT_WITH_LEFT, pointWithLeftMap);

        Map<CharType, State> pointWithoutLeftMap = new HashMap();
        pointWithoutLeftMap.put(CharType.CHAR_INTEGER_NUMBER, State.STATE_POINT_NUMBER);
        // STATE_POINT_WITHOUT_LEFT -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_POINT_WITHOUT_LEFT, pointWithoutLeftMap);

        Map<CharType, State> pointNumberMap = new HashMap();
        pointNumberMap.put(CharType.CHAR_INTEGER_NUMBER, State.STATE_POINT_NUMBER);
        pointNumberMap.put(CharType.CHAR_EXPONENT_E, State.STATE_EXPONENT_E);
        pointNumberMap.put(CharType.CHAR_END_FLAG, State.STATE_END);
        // STATE_POINT_NUMBER -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_POINT_NUMBER, pointNumberMap);

        Map<CharType, State> exponentEMap = new HashMap();
        exponentEMap.put(CharType.CHAR_PLUS_MINUS_SIGN, State.STATE_EXPONENT_PLUS_MINUS_SIGN);
        exponentEMap.put(CharType.CHAR_INTEGER_NUMBER, State.STATE_EXPONENT_NUMBER);
        // STATE_EXPONENT_E -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_EXPONENT_E, exponentEMap);

        Map<CharType, State> exponentPlusMinusSignMap = new HashMap();
        exponentPlusMinusSignMap.put(CharType.CHAR_INTEGER_NUMBER, State.STATE_EXPONENT_NUMBER);
        // STATE_EXPONENT_PLUS_MINUS_SIGN -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_EXPONENT_PLUS_MINUS_SIGN, exponentPlusMinusSignMap);

        Map<CharType, State> exponentNumberMap = new HashMap();
        exponentNumberMap.put(CharType.CHAR_INTEGER_NUMBER, State.STATE_EXPONENT_NUMBER);
        exponentNumberMap.put(CharType.CHAR_END_FLAG, State.STATE_END);
        // STATE_EXPONENT_NUMBER -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_EXPONENT_NUMBER, exponentNumberMap);

        Map<CharType, State> endMap = new HashMap();
        endMap.put(CharType.CHAR_END_FLAG, State.STATE_END);
        // STATE_END -> Map(字符->该字符从此状态可转移到的状态)
        transfer.put(State.STATE_END, endMap);

        int length = s.length();
        State state = State.STATE_INITIAL;

        for (int i = 0; i < length; i++) {
            CharType type = toCharType(s.charAt(i));
            if (!transfer.get(state).containsKey(type)) {
                return false;
            } else {
                state = transfer.get(state).get(type);
            }
        }
        // 能够达到自动机上的终态就成功了
        return state == State.STATE_INTEGER_NUMBER
                || state == State.STATE_POINT_WITH_LEFT
                || state == State.STATE_POINT_NUMBER
                || state == State.STATE_EXPONENT_NUMBER
                || state == State.STATE_END;
    }

    public CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_INTEGER_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXPONENT_E;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_PLUS_MINUS_SIGN;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    enum State {
        STATE_INITIAL,
        STATE_INTEGER_PLUS_MINUS_SIGN,
        STATE_INTEGER_NUMBER,
        STATE_POINT_WITH_LEFT,
        STATE_POINT_WITHOUT_LEFT,
        STATE_POINT_NUMBER,
        STATE_EXPONENT_E,
        STATE_EXPONENT_PLUS_MINUS_SIGN,
        STATE_EXPONENT_NUMBER,
        STATE_END,
    }

    enum CharType {
        CHAR_INTEGER_NUMBER,
        CHAR_EXPONENT_E,
        CHAR_POINT,
        CHAR_PLUS_MINUS_SIGN,
        CHAR_END_FLAG,
        CHAR_ILLEGAL,
    }

    /**
     * 【解法三：奇技淫巧（利用 Java 异常捕获）】
     * 处理特殊测试样例（以 f/F/d/D 结尾）："959440.94f"、"959440.94D"
     * @param s
     * @return
     */
    public boolean isNumber_03(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        if(s.endsWith("f") || s.endsWith("F") || s.endsWith("D") || s.endsWith("d")) {
            return false;
        }
        return true;
    }
}
