package com.example;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        int[] a = {1, 2, 3};
        int[] b = Arrays.copyOfRange(a, 0, a.length);

        Arrays.stream(b).forEach(System.out::println);


        Stack<Integer> stack = new Stack<>();

        LinkedList<Integer> list = new LinkedList<>();
        list.toArray(new Integer[0]);


        Map<String, List<Integer>> map2 = new HashMap<>();

        String s = "6791492645246929246386757141796333893017469234658722748742598348639065463771209243602696041603780296149289131476923691737519362771617464985577774171400314360942881749703195165891432072082340365383418580263111588256";
        System.out.println(s.length());

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