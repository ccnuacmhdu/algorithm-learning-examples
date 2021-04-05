package com.example.leetcode.lccup21;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 魔塔游戏
 */
public class Problem03 {
    public static void main(String[] args) {
//        int[] nums = {100,100,100,-250,-60,-140,-50,-50,100,150};
        int[] nums = {-200,-300,400,0};
        int res = magicTower(nums);
        System.out.println(res);
    }

    public static int magicTower(int[] nums) {
        int res = 0;
        long sum = 1L;
        PriorityQueue<Integer> lpq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        List<Integer> tmpList = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < 0) {
                lpq.add(nums[i]);
            }
            sum += nums[i];
            if(sum <= 0) {
                res++;
                int tmp = lpq.poll();
                tmpList.add(tmp);
                sum -= tmp;
            }
        }
        for(int i = 0; i < tmpList.size(); i++) {
            sum += tmpList.get(i);
        }
        res = sum <= 0 ? -1 : res;
        return res;
    }


}
