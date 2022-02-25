package com.example.math;

public class Leetcode_31 {
    /**
     * 下一个排列
     *
     * 例子，3, 6, 5, 4, 2, 1，从后往前找第一个升序位置(3)，从后往前找第一个大于3的数(4)并与之交换，3后面的数排序（逆序）
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) return;
        int n = nums.length;
        int incPos = n - 1;
        while(incPos > 0 && nums[incPos - 1] >= nums[incPos]) {
            incPos--;
        }
        if(incPos == 0) {
            reverse(nums, 0, n - 1);
        } else {
            int bigPos = n - 1;
            while(nums[bigPos] <= nums[incPos - 1]) {
                bigPos--;
            }
            swap(nums, bigPos, incPos - 1);
            reverse(nums, incPos, n - 1);
        }
    }
    private void swap(int[] a, int x, int y) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }
    private void reverse(int[] a, int from, int to) {
        // 1 2 3 -> 1 = (3 - 1) /2
        // 1 2 3 4 -> 1 = (4 - 1) / 2
        // 模仿常规的写
        // for(int i = 0; i <= (n - 1) / 2; i++) {
        //      swap(a, i, n - 1 - i);
        // }
        for(int i = 0; i <= (to - from) / 2; i++) {
            // to - i = (to - from + 1 - 1 - i) + from, n = to - from + 1
            swap(a, i + from, to - i);
        }
    }
}
