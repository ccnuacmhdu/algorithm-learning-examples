package com.example.codeInterview.chapter08;

public class Code_08_02 {
    /**
     * 学习下思路即可，因为这样的题目，有思路实现就很简单。。
     *
     * 例如：arr = [1,5,3,4,2,6,7]返回 4，因为只有[5,3,4,2]需要排序。
     * 直接把这个例子代入代码走一遍流程，就明白思路了。。
     *
     * @param arr
     * @return
     */
    public int getMinLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i != -1; i--) {
            if (arr[i] > min) {
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }
}
