package com.example;

public class Test {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int res = findMin(nums);
        System.out.println(res);
    }

    public static int findMin(int[] nums) {

        // 先举例观察
        // 1 2 3 4 5
        // 5 1 2 3 4
        // 4 5 1 2 3
        // 3 4 5 1 2
        // 2 3 4 5 1

        // 二分思路，和最右边数比较大小，判断最小的数在左边还是右边，决定二分往哪边走
        int l = 0;
        int r = nums.length - 1;
        int mid;
        int res = Integer.MAX_VALUE;
        while(l <= r) {
            mid = l + ((r - l) >> 1);
            if(nums[mid] <= nums[r]) {
                res = nums[mid] < res ? nums[mid] : res;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;


        // 常规思路
        // for(int i = 1; i < nums.length; i++){
        //     if(nums[i-1] > nums[i]){ // 若旋转过，必定出现此种情形
        //         return nums[i];
        //     }
        // }
        // return nums[0]; // 没旋转过
    }
}
