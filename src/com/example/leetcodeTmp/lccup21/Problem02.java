package com.example.leetcodeTmp.lccup21;

/**
 * 乐团站位
 */
public class Problem02 {
    public static void main(String[] args) {
        int num = 4;
        int xPos = 1;
        int yPos = 2;
        int res = orchestraLayout(num, xPos, yPos);
        System.out.println(res);
    }

    public static int orchestraLayout(int num, int xPos, int yPos) {
        long res = 0L;
        long newNum = (long) num;
        long newXPos = (long) xPos;
        long newYPos = (long) yPos;
//        int min = newNum / 2;
        long max = (newNum + 1) / 2;

        long originX = newXPos + 1;
        long originY = newYPos + 1;
        long minX = originX > max ? newNum + 1 - originX : originX;
        long minY = originY > max ? newNum + 1 - originY : originY;
        long c1 = Math.min(minX, minY) - 1; // 走过的外圈数，而所在的是第c1+1圈
        // n-0, n-2, n-4, n-6,   n - 2*(k-1), n - 2*(c1 - 1)
//        for(long i = 1; i <= c1; i++) {
//            res += 4 * (newNum - 2*(i-1)) - 4;
//        }

        res += -8*((1+c1)*c1/2) + 4*c1*(1+newNum);

        if(originX == c1 + 1) {
            res += originY - c1;
        } else if(originX == newNum - c1) {
            res += 2 * (newNum - 2*c1) - 1 + (newNum - c1 - originY);
        } else if(originY == c1 + 1){
            res += 3 * (newNum - 2*c1) - 2 + ((newNum - c1) - originX);
        } else {
            res += (newNum - 2*c1) + (originX - c1 - 1);
        }

        res = res % 9 == 0 ? 9 : res % 9;

        return (int) res;
    }
}
