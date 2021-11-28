package com.example.codeInterview.chapter03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Code_03_18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] pre = new int[n];
        int[] in = new int[n];
        for(int i = 0; i < n; i++) {
            pre[i] = scanner.nextInt();
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            in[i] = scanner.nextInt();
            indexMap.put(in[i], i);
        }
        int[] post = makePostArrayByPreAndIn(pre, in, indexMap);
        for(int i = 0; i < n; i++) {
            System.out.print(post[i] + " ");
        }
        System.out.println();
    }
    public static int[] makePostArrayByPreAndIn(int[] pre, int[] in, Map<Integer, Integer> indexMap) {
        if(pre == null || pre.length == 0) {
            return null;
        }
        int n = pre.length;
        int[] post = new int[n];
        process(pre, 0, n - 1, in, 0, n - 1, post, 0, n - 1, indexMap);
        return post;
    }
    private static void process(int[] pre, int preSt, int preEn, int[] in, int inSt, int inEn, int[] post, int postSt, int postEn, Map<Integer, Integer> indexMap) {
        if(preSt > preEn || inSt > inEn || postSt > postEn) {
            return;
        }
        post[postEn] = pre[preSt];
        int index = indexMap.get(pre[preSt]);
        process(pre, preSt + 1, preSt + index - inSt, in, inSt, index - 1, post, postSt, postSt + index - inSt - 1, indexMap);
        process(pre, preSt + index - inSt + 1, preEn, in, index + 1, inEn, post, postSt + index - inSt, postEn - 1, indexMap);
    }
}
