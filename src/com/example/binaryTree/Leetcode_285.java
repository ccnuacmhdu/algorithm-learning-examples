package com.example.binaryTree;

public class Leetcode_285 {

    private static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int value){
            this.value = value;
        }
    }

    public static Node getSuccessorNode(Node root){
        if(root == null){
            return root;
        }
        if(root.right != null){
            return getMostLeft(root.right);
        }else{
            Node parent = root.parent;
            //下面的parent != null一句，是为了下面图中的情况，一直向上判定完了都没有，后继是null
            while(parent != null && parent.left != root){
                root = parent;
                parent = root.parent;
            }
            return parent;
        }
    }

    private static Node getMostLeft(Node root){
        if(root == null){
            return root;
        }
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public static void main(String[] args){
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }

}
