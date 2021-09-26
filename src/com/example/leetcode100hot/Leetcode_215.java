package com.example.leetcode100hot;

public class Leetcode_215 {

    public int findKthLargest(int[] nums, int k) {
        if(nums == null || k < 1 || k > nums.length) {
            return 0;
        }
        return heapSort(nums, k);
    }
    // 堆排序
    private int heapSort(int[] a, int k) {
        for(int i = 0; i < a.length; i++) {
            heapInsert(a, i);
        }
        int size = a.length;
        int cnt = 0;
        while (size > 0) {
            swap(a, 0,  --size);
            if(++cnt == k) return a[size];
            heapify(a, size);
        }
        return 0;
    }
    private void heapInsert(int[] a, int idx) {
        while (a[idx] > a[(idx-1)/2]) {
            swap(a, idx, (idx-1)/2);
            idx = (idx-1) / 2;
        }
    }
    private void heapify(int[] a, int size) {
        int p = 0;
        int l = p * 2 + 1;
        int r;
        int max;
        int idx;
        while (l < size) {
            r = l + 1;
            max = Math.max(a[l], a[p]);
            idx = a[l] > a[p] ? l : p;
            if(r < size) {
                max = Math.max(max, a[r]);
                idx = max > a[r] ? idx : r;
            }
            if(idx == p) {
                break;
            }
            swap(a, idx, p);
            p = idx;
            l = p * 2 + 1;
        }
    }
    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Leetcode_215 leetcode_215 = new Leetcode_215();
        int[] nums = {5,2,4,1,3,6,0};
        leetcode_215.heapSort(nums, 4);
        for(int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }
        System.out.println();
    }
}