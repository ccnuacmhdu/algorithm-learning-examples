package com.example.hj;

import java.util.*;

/**
 * HJ3 明明的随机数
 */
public class HJ3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            set.add(arr[i]);
        }
        int[] ret = new int[set.size()];
        Iterator<Integer> iter = set.iterator();
        int idx = 0;
        while(iter.hasNext()) {
            ret[idx++] = iter.next();
        }
        Arrays.sort(ret);
        for(int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }
    }
}
