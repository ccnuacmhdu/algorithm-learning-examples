package com.example.hj;

import java.util.*;

/**
 * HJ88 扑克牌大小
 */
public class HJ88 {
    private static Map<String, Integer> map = getMap();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] ss = s.split("-");
            String[] ss1 = ss[0].split(" ");
            String[] ss2 = ss[1].split(" ");

            int n1 = ss1.length;
            int n2 = ss2.length;
            String ret = null;
            if(n1 == n2) {
                ret = (map.get(ss1[0]) > map.get(ss2[0])) ? ss[0] : ss[1];
            } else {
                if(s.contains("joker JOKER")) {
                    ret = "joker JOKER";
                } else if(ss1.length == 4) {
                    ret = ss[0];
                } else if(ss2.length == 4) {
                    ret = ss[1];
                } else {
                    ret = "ERROR";
                }
            }
            System.out.println(ret);
        }
    }
    private static Map<String, Integer> getMap() {
        Map<String, Integer> map = new HashMap<>();
        for(int i = 3; i <= 10; i++) {
            map.put(String.valueOf(i), i);
        }
        map.put("A", 14);
        map.put("2", 15);
        map.put("J", 11);
        map.put("Q", 12);
        map.put("K", 13);
        map.put("joker", 16);
        map.put("JOKER", 17);
        return map;
    }
}
