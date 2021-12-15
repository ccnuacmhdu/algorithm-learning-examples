package com.example.greedy;

public class Leetcode_55 {

    // 贪心
    public boolean canJump(int[] nums) {
        int mostRight = 0;
        // 达不到 mostRight 就不可能走到后面去
        for(int i = 0; i < nums.length && i <= mostRight; i++) {
            mostRight = Math.max(mostRight, i + nums[i]);
            if(mostRight >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

      // 模拟
//    public boolean canJump(int[] nums) {
//        int[] mem = new int[nums.length];
//        return process(nums, 0, mem);
//    }
//    private boolean process(int[] nums, int pos, int[] mem) {
//        if(pos >= nums.length - 1) return true;
//        if(mem[pos] != 0) return mem[pos] == 1;
//        boolean res = false;
//        for(int i = 1; i <= nums[pos]; i++) {
//            res = res || process(nums, pos + i, mem);
//        }
//        mem[pos] = res ? 1 : -1;
//        return res;
//    }
}
