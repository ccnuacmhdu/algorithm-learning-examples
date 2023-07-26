package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,4},{4,5}};
        int[][] ret = merge(intervals);
        for(int i = 0; i < ret.length; i++) {
            System.out.println(ret[i][0] + "," + ret[i][1]);
        }
    }

    public static int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        List<int[]> list = new ArrayList<>();
        int i = 0;
        while(i < intervals.length) {
            int st = intervals[i][0];
            int en = intervals[i][1];
            int j = i + 1;
            boolean flag = false;
            for(; j < intervals.length; j++) {
                if(intervals[j][0] <= en) {
                    en = Math.max(intervals[j][1], en);
                    flag = true;
                } else {
                    break;
                }
            }
            list.add(new int[]{st, en});
            i = flag ? j : ++i;
        }
        return list.toArray(new int[list.size()][2]);
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}