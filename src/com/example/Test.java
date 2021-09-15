package com.example;

import java.awt.datatransfer.FlavorListener;
import java.sql.Struct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        Solution solution = new Solution();
        List<List<Integer>> res = solution.permute(arr);
        for(int i = 0; i < res.size(); i++) {
            for(int j = 0; j < res.get(i).size(); j++) {
                System.out.print(res.get(i).get(j) + "\t");
            }
            System.out.println();
        }

        System.out.println("test");

        System.out.println("test continue");
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        process(nums, 0, list,lists);
        return lists;
    }
    private void process(int[] nums, int st, List<Integer> list, List<List<Integer>> lists) {
        if(st == nums.length) {
            lists.add(new ArrayList<>(list));
        }
        for(int i = st; i < nums.length; i++) {
            list.add(nums[i]);
            swap(nums, st, i);
            process(nums, st + 1, list, lists);
            list.remove(list.size()-1);
            swap(nums, st, i);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}