package com.example.codeinterview.chapter03;

import java.util.*;

public class Code_03_06 {
    private static class Node {
        public int value;
        public Node leftChild;
        public Node rightChild;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int root = scanner.nextInt();
        List<Info> infos = new ArrayList<>();
        int parent, leftChild, rightChild;
        for(int i = 0; i < n; i++) {
            parent = scanner.nextInt();
            leftChild = scanner.nextInt();
            rightChild = scanner.nextInt();
            infos.add(new Info(parent, leftChild, rightChild));
        }
        Node tree = createBTree(root, infos);
        int maxSize = process(tree).maxSize;
        System.out.println(maxSize);
    }

    private static class ReturnType {
        public int min;
        public int max;
        public int maxSize;
        public Node maxBSTHead;

        public ReturnType(int min, int max, int maxSize, Node maxBSTHead) {
            this.min = min;
            this.max = max;
            this.maxSize = maxSize;
            this.maxBSTHead = maxBSTHead;
        }
    }
    public static ReturnType process(Node root) {
        if(root == null) {
            return new ReturnType(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, null);
        }
        ReturnType lData = process(root.leftChild);
        ReturnType rData = process(root.rightChild);
        int min = Math.min(root.value, Math.min(lData.min, rData.min));
        int max = Math.max(root.value, Math.max(lData.max, rData.max));
        int maxSize = Math.max(lData.maxSize, rData.maxSize);
        // 最大搜索子树可能是最大搜索左子树，最大搜索右子树，或者就是最大搜索左子树+最大搜索右子树+树根root
        Node maxBSTHead = lData.maxSize >= rData.maxSize ? lData.maxBSTHead : root.rightChild;
        if(lData.max < root.value && root.value < rData.min
                && lData.maxBSTHead == root.leftChild && rData.maxBSTHead == root.rightChild) {
            maxSize = lData.maxSize + rData.maxSize + 1;
            maxBSTHead = root;
        }
        return new ReturnType(min, max, maxSize, maxBSTHead);
    }

    private static class Info {
        public int parent;
        public int leftChild;
        public int rightChild;

        public Info(int parent, int leftChild, int rightChild) {
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
    private static Node getNode(int v, Map<Integer, Node> map) {
        if(v == 0) {
            return null;
        }
        if(!map.containsKey(v)) {
            map.put(v, new Node(v));
        }
        return map.get(v);
    }
    private static Node createBTree(int root, List<Info> infos) {
        Map<Integer, Node> map = new HashMap<>();
        map.put(root, new Node(root));
        Node parent;
        for(Info info: infos) {
            parent = getNode(info.parent, map);
            parent.leftChild = getNode(info.leftChild, map);
            parent.rightChild = getNode(info.rightChild, map);
        }
        return map.get(root);
    }
}
