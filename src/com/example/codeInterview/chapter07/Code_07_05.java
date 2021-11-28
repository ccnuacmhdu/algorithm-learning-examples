package com.example.codeInterview.chapter07;

public class Code_07_05 {

    // 只有一个数出现奇数次
    public void printOddTimesNum1(int[] arr) {
        int eO = 0;
        for (int cur : arr) {
            eO ^= cur;
        }
        System.out.println(eO);
    }

    // 只有两个数出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eO = 0, eOhasOne = 0;
        for (int curNum : arr) {
            eO ^= curNum;
        }
        int rightOne = eO & (~eO + 1);
        for (int cur : arr) {
            if ((cur & rightOne) != 0) {
                eOhasOne ^= cur;
            }
        }
        System.out.println(eOhasOne + " " + (eO ^ eOhasOne));
    }
}
