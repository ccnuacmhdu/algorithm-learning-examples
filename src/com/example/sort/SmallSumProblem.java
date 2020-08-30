package com.example.sort;

/**
 * 【小和问题】
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。
 *
 * 例如数组 [1,3,4,2,5]，
 * 1 左边比 1 小的数 [];
 * 3 左边比 3 小的数 [1];
 * 4 左边比 4 小的数 [1,3];
 * 2 左边比 2 小的数 [1];
 * 5 左边比 5 小的数 [1,3,4,2];
 * 所以小和为 1+1+3+1+1+3+4+2 = 16
 *
 * 【求数组小和思路】
 * 常规暴力求解法时间复杂度是 O(N^2)，这里考虑了归并排序把时间复杂度下降到 O(NlogN)。
 * 关键思想是直接计算出贡献值，比如 1，2，3，4，这样的有序序列，按照常规暴力求法是，1 + (1+2) + (1+2+3)，
 * 按照贡献的话，对于 1，2，3，4 这样有序序列，1 对 2，3，4 均有贡献，2对 3，4 均有贡献，3 对 4 有贡献，
 * 那么 1*3+2*2+3*1 就可以了。而归并排序是对两个有序序列的合并，如果左侧当前值严格小于右侧当前值的话，
 * 就可以直接求出左侧当前值对右侧所有相应值的贡献。这样通过归并排序就可以解决问题了。
 */
public class SmallSumProblem {

    public static int mergeSort(int[] a){
        if(a == null || a.length < 2){
            return 0;
        }
        return mergeSort(a, 0, a.length-1);
    }

    public static int mergeSort(int[] a, int left, int right){
        if(left == right){
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return mergeSort(a, left, mid)
                + mergeSort(a, mid+1, right)
                + merge(a, left, mid, right);
    }
    public static int merge(int[] a, int left, int mid, int right){
        int[] help = new int[right-left+1];
        int index = 0;
        int i = left;
        int j = mid + 1;
        int res = 0;
        while(i <= mid && j <= right){
            if(a[i] < a[j]){
                res += a[i]*(right-j+1);
                help[index++] = a[i++];
            }else{
                help[index++] = a[j++];
            }
        }
        while(i <= mid){
            help[index++] = a[i++];
        }
        while(j <= right){
            help[index++] = a[j++];
        }
        for(int k = 0; k < help.length; k++){
            a[k+left] = help[k];
        }
        return res;
    }
}
