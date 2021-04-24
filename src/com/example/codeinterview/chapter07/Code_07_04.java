package com.example.codeinterview.chapter07;

// [leetcode] 剑指 Offer 15. 二进制中1的个数
public class Code_07_04 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res += (n & 1);
            n = n >>> 1;    // >>> 重要，无符号右移，防止循环移位，死循环
        }
        return res;
    }

    /**
     * 例如，n=01000100，n-1=01000011，n&(n-1)=01000000，说明处理到 01000100 之后，下一
     * 步还得处理，因为 01000000!=0。n=01000000，n-1=00111111，n&(n-1)=00000000，说明处理到
     * 01000000 之后，下一步就不用处理了，因为接下来没有 1。
     *
     * 所以，n&=(n-1)操作的实质是抹掉最右边的 1。
     *
     * @param n
     * @return
     */
    public int hammingWeight2(int n) {
        int res = 0;
        while(n != 0) {
            res++;
            n &= (n-1);
        }
        return res;
    }

    /**
     * 每次进行 n-=n&(~n+1)操作时，这也是移除最右侧的 1 的过程。等号右边 n & (~n + 1)的含义
     * 是得到 n 中最右侧的 1。
     *
     * @param n
     * @return
     */
    public int hammingWeight3(int n) {
        int res = 0;
        while(n != 0) {
            res++;
            n -= (n & (~n + 1));
        }
        return res;
    }

}
