package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] a = {3,2,1,5,6,4};
        int k = 2;
        int ret = findKthLargest(a, 2);
        System.out.println(ret);
    }

    public static int findKthLargest(int[] nums, int k) {
        mergeSort(nums);
        return nums[nums.length - k];
    }

    private static void mergeSort(int[] a) {
        if(a == null || a.length < 2) return;
        mergeSort(a, 0, a.length - 1);
    }
    private static void mergeSort(int[] a, int st, int en) {
        if(st >= en) return;
        int mid = st + ((en - st) >> 1);
        mergeSort(a, st, mid);
        mergeSort(a, mid + 1, en);
        merge(a, st, mid, en);
    }
    private static void merge(int[] a, int st, int mid, int en) {
        int[] tmp = new int[en - st + 1];
        int cnt = 0;
        int l = 0, r = mid + 1;
        while(l <= mid && r <= en) {
            if(a[l] <= a[r]) {
                tmp[cnt++] = a[l++];
            } else {
                tmp[cnt++] = a[r++];
            }
        }
        while(l <= mid) {
            tmp[cnt++] = a[l++];
        }
        while(r <= en) {
            tmp[cnt++] = a[r++];
        }
        for(int k = 0; k < cnt; k++) {
            a[k + st] = tmp[k];
        }
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//
//    TreeNode() {
//    }
//
//    TreeNode(int val) {
//        this.val = val;
//    }
//
//    TreeNode(int val, TreeNode left, TreeNode right) {
//        this.val = val;
//        this.left = left;
//        this.right = right;
//    }
//}