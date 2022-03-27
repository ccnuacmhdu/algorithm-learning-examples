package com.example.hj;

import java.util.*;

public class HJ89 {
    private static String[] digit = {"", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static Map<String, Integer> map = getDigitMap();
    private static String[] operator = {"+", "-", "*", "/"};
    private static String[][] operatorExp = getOperatorExp();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String s = scanner.nextLine();
            if(s.contains("joker") || s.contains("JOKER")) {
                System.out.println("ERROR");
                continue;
            }
            String[] ss = s.split(" ");
            int[] arr = new int[4];
            for(int i = 0; i < 4; i++) {
                arr[i] = map.get(ss[i]);
            }
            List<List<Integer>> lists = new ArrayList<>();
            permutation(arr, 0, lists, new ArrayList<>());
            String ret = compute(lists);
            System.out.println(ret);
        }
    }

    private static String compute(List<List<Integer>> lists) {
        for(List<Integer> list : lists) {
            for(String[] exp : operatorExp) {
                int ret = operate(exp[0], list.get(0), list.get(1));
                for(int i = 1; i < exp.length; i++) {
                    ret = operate(exp[i], ret, list.get(i + 1));
                }
                if(ret == 24) {
                    StringBuffer sb = new StringBuffer();
                    for(int k = 0; k < exp.length; k ++) {
                        sb.append(digit[list.get(k)]).append(exp[k]);
                    }
                    sb.append(digit[list.get(list.size() - 1)]);
                    return sb.toString();
                }
            }
        }
        return "NONE";
    }

    private static int operate(String op, int i, int j) {
        if("+".equals(op)) {
            return i + j;
        } else if ("-".equals(op)) {
            return i - j;
        } else if ("*".equals(op)) {
            return i * j;
        } else {
            return i / j;
        }
    }

    private static String[][] getOperatorExp() {
        String[][] operatorExp = new String[4*4*4][3];
        int idx = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    operatorExp[idx++] = new String[]{operator[i], operator[j], operator[k]};
                }
            }
        }
        return operatorExp;
    }

    private static void permutation(int[] arr, int st, List<List<Integer>> lists, List<Integer> list) {
        if(st == arr.length) {
            lists.add(new ArrayList<Integer>(list));
        }
        for(int k = st; k < arr.length; k++) {
            swap(arr, k, st);
            list.add(arr[st]);
            permutation(arr, st + 1, lists, list);
            swap(arr, k, st);
            list.remove(list.size() - 1);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static Map<String, Integer> getDigitMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("J", 11);
        map.put("Q", 12);
        map.put("K", 13);
        for(int i = 2; i <= 10; i++) {
            map.put(String.valueOf(i), i);
        }
        return map;
    }
}
