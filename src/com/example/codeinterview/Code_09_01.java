package com.example.codeinterview;

// [leetcode] 470. 用 Rand7() 实现 Rand10()
public class Code_09_01 {

    public static void main(String[] args) {
        Code_09_01 code = new Code_09_01();
//        System.out.println(code.rand7());
//        System.out.println(code.rand1());
        System.out.println(code.rand10());
    }

    public int rand10() {
        int[] digits = new int[4];
        int ans = 11;
        while(ans > 10) {
            ans = 0;
            for(int i = 0; i < digits.length; i++) {
                digits[i] = rand1();
                digits[i] <<= i;
                ans |= digits[i];
            }
            ans += 1;
        }
        return ans;
    }

    private int rand1() {
        int rand = rand7();
        while (rand == 7) {
            rand = rand7();
        }
        if(rand > 3) {
            return 0;
        } else {
            return 1;
        }
    }

    private int rand7() {
        return (int)(Math.random()*7) + 1;
    }
}
