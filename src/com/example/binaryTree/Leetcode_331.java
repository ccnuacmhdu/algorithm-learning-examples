package com.example.binaryTree;

/**
 * 根据二叉树相关公式关系，
 *  n = n0 + n1 + n2
 *  e = n1 + n2 * 2
 *  n = e + 1
 *  n0 = n2 + 1
 *
 *  不难得到，
 *  null数 = n1 + n0 * 2 = n1 + n2 * 2 + 2
 *  节点数 = n0 + n1 + n2 = n1 + n2 * 2 + 1
 *
 *  null数 = 节点数 + 1（完全遍历后，必须满足这个关系）
 *
 *  先序遍历到任何时候，都应该满足是二叉树的基本要求，# 不能过多（不再是二叉树）
 */
public class Leetcode_331 {
    public boolean isValidSerialization(String preorder) {
        String[] ss = preorder.split(",");
        int degrees = 1;    // null数 = 节点数 + 1（完全遍历后，必须满足这个关系）
        for(String s : ss) {
            if(degrees <= 0) {  // null 过多，不满足二叉树基本要求
                return false;
            }
            if("#".equals(s)) {
                degrees--;
            } else {
                degrees++;
            }
        }
        return degrees == 0;
    }
}
