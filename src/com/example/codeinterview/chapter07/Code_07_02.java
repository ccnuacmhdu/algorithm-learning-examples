package com.example.codeinterview.chapter07;

public class Code_07_02 {
    public int flip(int n) {
        return n ^ 1;
    }
    public int sign(int n) {
        return flip((n >> 31) & 1);
    }
    public int getMax1(int a, int b) {
        int c = a - b;
        int scA = sign(c);
        int scB = flip(scA);
        return a * scA + b * scB;
    }

    /**
     * 解决 getMax1 有可能溢出的问题
     *
     * 如果 a 的符号与 b 的符号不同（difSab==1，sameSab==0），则有：
     *   如果 a 为 0 或正，那么 b 为负（sa==1，sb==0），应该返回 a；
     *   如果 a 为负，那么 b 为 0 或正（sa==0，sb==1），应该返回 b。
     *
     * 如果 a 的符号与 b 的符号相同（difSab==0，sameSab==1），这种情况下，a-b 的值绝对不会溢出：
     *   如果 a-b 为 0 或正（sc==1），返回 a；
     *   如果 a-b 为负（sc==0），返回 b；
     *
     * 综上所述，应该返回 a * (difSab * sa + sameSab * sc) + b * flip(difSab * sa + sameSab * sc)。
     *
     * @param a
     * @param b
     * @return
     */
    public int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;
        int sameSab = flip(difSab);
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }
}
