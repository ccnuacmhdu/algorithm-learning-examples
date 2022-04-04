package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();
            if(s1 == null || s2 == null || s1.trim().length() == 0 || s2.trim().length() == 0) {
                System.out.println(0);
                continue;
            }
            String[] digits = s1.split(" ");
            String[] colors = s2.split(" ");
            int n = digits.length;
            int max = 0;
            for(int i = 0; i < n; i++) {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                for(int k = 0; k < 10; k++) {
                    boolean flag = false;
                    for(int j = 0; j < n; j++) {
                        if(!set.contains(j)) {
                            boolean has = false;
                            Iterator<Integer> iter = set.iterator();
                            while (iter.hasNext()) {
                                int id = iter.next();
                                if(digits[id].equals(digits[j]) || colors[id].equals(colors[j])) {
                                    has = true;
                                    flag = true;
                                    break;
                                }
                            }
                            if(has) set.add(j);
                        }
                    }
                    if(!flag) break;
                }
                max = Math.max(max, set.size());
            }
            System.out.println(max);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
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