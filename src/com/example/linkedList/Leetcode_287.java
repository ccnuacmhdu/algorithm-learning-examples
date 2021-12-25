package com.example.linkedList;

public class Leetcode_287 {
    /*
    *  快慢指针证明
    *
    *  设表头节点是 S，入环节点是 C，相遇在节点 M，|SC| = m，|CM| = k，环的长度是 N
    *  相遇时，slow 走 d1 = m + k + N*a
    *  相遇时，fast 走 d2 = m + k + N*b
    *  又因为 2 * d1 = d2，那么有 m + k 是 N 的整数倍
    *  相遇后，置 fast 到 C，slow 在 M，之后 slow 和 fast 都每次走一步，当 slow 走
    *  到 C 时，fast 走的距离是 m，slow 也走了 m，但 slow 相当于从 C 走了 m + k，也就是说此时 slow 回到了 C，slow 和 fast 相遇了！
    *
    * */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
