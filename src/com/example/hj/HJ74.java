package com.example.hj;

import java.util.*;

/**
 * HJ74 参数解析
 */
public class HJ74 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            char[] cs = scanner.nextLine().toCharArray();
            StringBuffer sb = new StringBuffer();
            int flag = 0;
            int count = 1;
            for (int i = 0; i < cs.length; i++) {
                if (cs[i] == '\"') {
                    flag++;
                    continue;
                }
                if (cs[i] != ' ') {
                    sb.append(cs[i]);
                }
                if (cs[i] == ' ' && flag % 2 != 0) {
                    sb.append(cs[i]);
                }
                if (cs[i] == ' ' && flag % 2 == 0) {
                    sb.append("\n");
                    count++;
                }
            }
            System.out.println(count + "\n" + sb.toString());
        }
    }
}