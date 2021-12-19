package com.example.design;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_146 {
    /**
     设计思路：考虑清楚 get 和 put 方法的步骤即可
     */
    class LRUCache {

        private class DNode {
            public int k;
            public int v;
            public DNode pre;
            public DNode next;

            public DNode(int k, int v) {
                this.k = k;
                this.v = v;
            }
        }

        private DNode head;
        private DNode tail;
        private Map<Integer, DNode> map;
        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            head = new DNode(-1, -1);
            tail = new DNode(-1, -1);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if(map.containsKey(key)) {
                DNode t = map.get(key);
                move2Head(t);
                return t.v;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)) {
                DNode t = map.get(key);
                move2Head(t);
                t.v = value;
            } else {
                if(map.size() == capacity) {
                    int k = tail.pre.k; // 先取出来，重要！
                    remove(tail.pre);
                    map.remove(k);  // map.remove(tail.pre.k); 这样是错的！tail.pre 已经变了哦！
                }
                DNode t = new DNode(key, value);
                addHead(t);
                map.put(key, t);
            }
        }

        private void remove(DNode t) {
            t.pre.next = t.next;
            t.next.pre = t.pre;
            t.next = null;
            t.pre = null;
        }
        private void addHead(DNode t) {
            t.next = head.next;
            t.next.pre = t;
            t.pre = head;
            head.next = t;
        }
        private void move2Head(DNode t) {
            remove(t);
            addHead(t);
        }

    }
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
}
