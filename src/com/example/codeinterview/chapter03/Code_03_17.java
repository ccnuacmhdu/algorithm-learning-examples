package com.example.codeinterview.chapter03;

import java.util.*;

public class Code_03_17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int root = scanner.nextInt();
        int[] happy = new int[n+1];
        for(int i = 1; i <= n; i++) {
            happy[i] = scanner.nextInt();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 1; i <= n - 1; i++) {
            int high = scanner.nextInt();
            int low = scanner.nextInt();
            if(!map.containsKey(high)) {
                map.put(high, new ArrayList<>());
            }
            map.get(high).add(low);
        }
        int res = getMaxHappy(root, map, happy);
        System.out.println(res);
    }

    private static class ReturnType {
        public int yes; // 取当前节点能得到的最大值
        public int no;  // 不取当前节点得到的最大值

        public ReturnType(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }
    private static ReturnType process(int root, Map<Integer, List<Integer>> map, int[] happy) {
        int yes = happy[root];
        int no = 0;
        if(!map.containsKey(root)) {
            return new ReturnType(yes, no);
        } else {
            for(Integer integer: map.get(root)) {
                ReturnType subData = process(integer, map, happy);
                yes += subData.no;
                no += Math.max(subData.no, subData.yes);
            }
            return new ReturnType(yes, no);
        }
    }
    public static int getMaxHappy(int root, Map<Integer, List<Integer>> map, int[] happy) {
        ReturnType returnType = process(root, map, happy);
        return Math.max(returnType.no, returnType.yes);
    }

}

