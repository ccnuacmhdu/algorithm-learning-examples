package com.example.codeinterview.chapter04;

import java.util.List;

// [leetcode] 面试题 08.06. 汉诺塔问题
public class Code_04_07 {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        if(A == null || A.size() == 0) {
            return;
        }
        hanota(A, B, C, A.size());
    }
    private void hanota(List<Integer> A, List<Integer> B, List<Integer> C, int n) {
        if(n == 1) {
            C.add(A.remove(A.size()-1));
            return;
        }
        hanota(A, C, B, n - 1);
        C.add(A.remove(A.size()-1));
        hanota(B, A, C, n - 1);
    }
}
