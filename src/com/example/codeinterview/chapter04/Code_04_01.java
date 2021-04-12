package com.example.codeinterview.chapter04;

// [leetcode] 509. 斐波那契数
// [leetcode] 剑指 Offer 10- II. 青蛙跳台阶问题
public class Code_04_01 {

    // 斐波那契数（O(N)解法）
    public int fib(int n) {
        if(n < 2) {
            return n;
        }
        int a = 0;
        int b = 1;
        int c = a + b;
        for(int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    // 斐波那契数（递归解法）
    /*public int fib(int n) {
        if(n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }*/


    // 青蛙跳台阶（O(N)解法）
    public int numWays(int n) {
        if(n < 2) {
            return 1;
        }
        int a = 1;
        int b = 1;
        int c = a + b;
        for(int i = 2; i <= n; i++) {
            c = (a % 1000000007 + b % 1000000007) % 1000000007;
            a = b;
            b = c;
        }
        return c;
    }

    // 青蛙跳台阶（超时）
   /* public int numWays(int n) {
        if(n < 2) {
            return 1;
        }
        return (numWays(n - 1) % 1000000007 + numWays(n - 2) % 1000000007) % 1000000007;
    }*/
}
