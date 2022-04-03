package com.example.hj;

import java.util.*;

/**
 * HJ77 火车进站
 */
public class HJ77 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            List<List<Integer>> lists = new ArrayList<>();
            permutation(a, 0, lists, new ArrayList<Integer>());
            List<String> ret = new ArrayList<>();
            for(List<Integer> list : lists) {
                if(check(list, a)) {
                    ret.add(list2S(list));
                }
            }
            ret.sort(String::compareTo);
            for(String s : ret) {
                System.out.println(s);
            }
        }
    }
    private static boolean check(List<Integer> list, int[] a) {
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for(int i = 0; i < a.length; i++) {
            stack.push(a[i]);
            while (!stack.isEmpty() && stack.peek() == list.get(cnt)) {
                stack.pop();
                cnt++;
            }
        }
        if(cnt == list.size()) {
            return true;
        }
        return false;
    }
    private static String list2S(List<Integer> list) {
        StringBuffer sb = new StringBuffer();
        for(int i : list) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }
    private static void permutation(int[] a, int st, List<List<Integer>> lists, List<Integer> list) {
        if(st == a.length) {
            lists.add(new ArrayList<>(list));
        }
        for(int i = st; i < a.length; i++) {
            swap(a, i, st);
            list.add(a[st]);
            permutation(a, st + 1, lists, list);
            swap(a, i, st);
            list.remove(list.size() - 1);
        }
    }
    private static void  swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
