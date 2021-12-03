package com.example.dynamicProgramming;

public class Leetcode_486 {

    // 动态规划（互相填写二维表格）
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];
        for(int i = 0; i < n; i++) {
            f[i][i] = nums[i];
        }
        for(int i = n - 2; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(s[i+1][j] + nums[i], s[i][j-1] + nums[j]);
                s[i][j] = Math.min(f[i+1][j], f[i][j-1]);
            }
        }
        return f[0][n-1] >= s[0][n-1];
    }

//    // 递归
//    public boolean PredictTheWinner(int[] nums) {
//        int fScore = f(nums, 0, nums.length - 1);
//        int sScore = s(nums, 0, nums.length - 1);
//        return fScore >= sScore;
//    }
//    private int f(int[] nums, int st, int en) {
//        if(st == en) {
//            return nums[st];
//        }
//        // 先手拿取后手最大的（先手和后手都聪明）
//        return Math.max(
//                nums[st] + s(nums, st + 1, en),
//                nums[en] + s(nums, st, en - 1)
//            );
//    }
//    private int s(int[] nums, int st, int en) {
//        if(st == en) {
//            return 0;
//        }
//        // 举个例子，nums = {1,2} 帮助理解，虽说后手聪明，但先手也聪明，先手必然拿走最大的，后手只能拿先手最小的
//        return Math.min(
//               f(nums, st + 1, en),
//               f(nums, st, en - 1)
//            );
//    }

}
