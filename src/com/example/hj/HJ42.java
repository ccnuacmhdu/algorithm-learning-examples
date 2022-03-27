package com.example.hj;

import java.util.*;

/**
 * HJ42 学英语
 */
public class HJ42 {
    private static Map<Long, String> map = getMap();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            long  l = scanner.nextLong();
            List<String> ret = new ArrayList<>();
            int cnt = 0;
            while(l != 0) {
                long mod = l % 1000;
                List<String> parseRet = parse(mod);
                ret.addAll(parseRet);
                l /= 1000;
                if(l == 0) break;
                if(cnt == 0) {
                    ret.add("thousand");
                } else if(cnt == 1) {
                    ret.add("million");
                } else if(cnt == 2) {
                    ret.add("billion");
                }
                cnt++;
            }
            StringBuffer sb = new StringBuffer();
            for(int i = ret.size() - 1; i >= 0; i--) {
                sb.append(ret.get(i)).append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    private static Map<Long, String> getMap() {
        Map<Long, String> map = new HashMap<>();
        map.put(1L, "one");
        map.put(2L, "two");
        map.put(3L, "three");
        map.put(4L, "four");
        map.put(5L, "five");
        map.put(6L, "six");
        map.put(7L, "seven");
        map.put(8L, "eight");
        map.put(9L, "nine");
        map.put(10L, "ten");
        map.put(11L, "eleven");
        map.put(12L, "twelve");
        map.put(13L, "thirteen");
        map.put(14L, "fourteen");
        map.put(15L, "fifteen");
        map.put(16L, "sixteen");
        map.put(17L, "seventeen");
        map.put(18L, "eighteen");
        map.put(19L, "nineteen");
        map.put(20L, "twenty");
        map.put(30L, "thirty");
        map.put(40L, "forty");
        map.put(50L, "fifty");
        map.put(60L, "sixty");
        map.put(70L, "seventy");
        map.put(80L, "eighty");
        map.put(90L, "ninety");
        return map;
    }

    private static List<String> parse(long num) {
        List<String> list = new ArrayList<>();
        long b = num % 100;
        if(b > 0 && b <= 20) {
            list.add(map.get(b));
        } else if(b > 20) {
            long c = b % 10;
            if(c != 0) {
                list.add(map.get(c));
            }
            list.add(map.get(b - c));
        }
        long a = num / 100;
        if(a != 0) {
            if(b != 0) {
                list.add("and");
            }
            list.add("hundred");
            list.add(map.get(a));
        }
        return list;
    }
}
