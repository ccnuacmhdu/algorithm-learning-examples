package com.example.binarySearch;

/**
 * 局部最小值问题（一数组中，任意两个相邻的数都不一样，找到一个局部最小值。）
 *
 * 局部最小值的三种情况：
 * （1）a[0] < a[1]，a[0]就是局部最小；
 * （2）a[n-1] < a[n-2]， a[n-1]就是局部最小；
 * （3）a[i-1] > a[i] && a[i] < a[i+1]，a[i]就是局部最小。
 *
 * 【解决思路】
 * 如果两头都不是局部最小，那么头部下降趋势，而尾部是上升趋势，那么在中间必定存在局部最小值。
 * 如果 a[mid] > a[mid-1] || a[mid] > a[mid+1]，那么 a[mid]不是局部最小，那么在
 * a[l…mid-1]，或 a[mid+1, r] 必有局部最小，不断二分即可。
 *
 * 疑问：下面代码中用到 mid-1 和 mid+1，会发生数组越界吗？
 * 不会，因为进入二分的前提是数组头尾都不是局部最小，那么在二分过程中，除了数组头尾那两个数，
 * 中间任何一个数的下标都是处于 [1, a.length-2] 的，所以 mid+1 和 mid-1 不会越界。
 *
 * 【测试样例】
 * 1000,900,600,300,900,1000 （局部最小为 300）
 */
public class BinarySearchExample02 {
    public static int binarySearchLocalMinimum(int[] a) {
        if(null == a || a.length <= 1) {
            return -1;
        }
        if(a[0] < a[1]) {
            return 0;
        }
        if(a[a.length-1] < a[a.length-2]) {
            return a.length - 1;
        }
        int l = 0;
        int r = a.length - 1;
        while(l <= r) {
            int mid = l + ((r-l)>>1);
            if(a[mid] > a[mid-1]) {
                r = mid - 1;
            } else if(a[mid] > a[mid+1]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
