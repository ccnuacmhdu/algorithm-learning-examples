package com.example.binaryTree;

public class Leetcode_331 {
    public boolean isValidSerialization(String preorder) {
        String[] ss = preorder.split(",");
        // 边数比节点数少1
        int edges = 1;
        for(String s : ss) {
            if(edges <= 0) {
                return false;
            }
            // 每个节点都会消耗1个边
            edges--;
            // 每个非#节点都会提供两个边
            if(!"#".equals(s)) {
                edges += 2;
            }
        }
        return edges == 0;
    }
}
