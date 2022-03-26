package com.example.hj;

import java.util.*;

/**
 * HJ95 人民币转换
 */
public class HJ95 {
    private static String[] chn = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static String[] unit = {null, "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String str = scanner.next();
            String integer = null, decimal = null;
            if(str.contains(".")) {
                String[] splits = str.split("\\.");
                integer = splits[0];
                decimal = splits[1];
            } else {
                integer = str;
            }
            String integerFormat = format(integer);
            StringBuffer sb = new StringBuffer(integerFormat);
            if(!integerFormat.equals("人民币")) {
                sb.append("元");
            }
            if(decimal == null || decimal.equals("00")) {
                sb.append("整");
            } else {
                int digit1 = Integer.valueOf(String.valueOf(decimal.charAt(0)));
                int digit2 = Integer.valueOf(String.valueOf(decimal.charAt(1)));
                if(digit1 != 0) {
                    sb.append(chn[digit1]).append("角");
                }
                if(digit2 != 0) {
                    sb.append(chn[digit2]).append("分");
                }
            }
            System.out.println(sb.toString());
        }
    }
    private static String format(String s) {
        StringBuffer sb = new StringBuffer();
        sb.append("人民币");
        if(s == null || s.equals("") || s.equals("0")) {
            return sb.toString();
        }
        boolean hasZero = false;
        for(int i = 0; i < s.length(); i++) {
            int n = Integer.valueOf(String.valueOf(s.charAt(i)));
            int unitIdx = s.length() - 1 - i;
            if(n == 0) {
                if(!hasZero && unitIdx != 4) {
                    hasZero = true;
                    sb.append(chn[0]);
                }
            } else if(n == 1) {
                if(!"拾".equals(unit[unitIdx])) {
                    sb.append(chn[1]);
                }
                hasZero = false;
            } else {
                sb.append(chn[n]);
                hasZero = false;
            }
            if(unit[unitIdx] != null && !hasZero) {
                sb.append(unit[unitIdx]);
            }
        }
        if(sb.charAt(sb.length() - 1) == '零') {
            return sb.substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }
    }
}
