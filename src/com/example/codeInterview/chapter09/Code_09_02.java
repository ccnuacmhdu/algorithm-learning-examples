package com.example.codeInterview.chapter09;

public class Code_09_02 {
    // 因为进行 1×2×3×…×N 操作的过程中，因子 2 的数目比因子 5 的数目多，所以不管有多少个因子 5，
    // 都有足够的因子 2 与其相乘得到 10。所以只要找出 1~N 所有的数中一共含有多少个因子 5 就可以。
    public int zeroNum1(int num) {
        if (num < 0) {
            return 0;
        }
        int res = 0;
        int cur = 0;
        for (int i = 5; i < num + 1; i = i + 5) {
            cur = i;
            while (cur % 5 == 0) {
                res++;
                cur /= 5;
            }
        }
        return res;
    }

    // 把 [1, 20] 带入，观察规律就明白了
    public int zeroNum2(int num) {
        if (num < 0) {
            return 0;
        }
        int res = 0;
        while (num != 0) {
            res += num / 5;
            num /= 5;
        }
        return res;
    }
}
