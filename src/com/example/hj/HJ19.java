package com.example.hj;

import java.util.*;

/**
 * HJ19 简单错误记录
 */
public class HJ19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] ss = s.split(" ");
            String[] ss1 = ss[0].split("\\\\");
            String fileName = ss1[ss1.length - 1];
            String fileNameShort = fileName.length() <= 16 ? fileName : fileName.substring(fileName.length() - 16, fileName.length());
            String id = fileNameShort + " " + ss[1];
            if(!map.containsKey(id)) {
                list.add(id);
            }
            map.put(id, map.getOrDefault(id, 0) + 1);
        }
        int st = list.size() > 8 ? (list.size() - 8) : 0;
        for(int i = st; i < list.size(); i++) {
            String k = list.get(i);
            Integer v = map.get(k);
            System.out.println(k + " " + v);
        }
    }
}
