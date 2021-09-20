package com.example.leetcode100hot;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_146 {
    class LRUCache {
        private class DlinkedNode {
            int k, v;
            DlinkedNode pre, next;

            DlinkedNode() {
            }

            DlinkedNode(int k, int v) {
                this.k = k;
                this.v = v;
            }
        }

        private Map<Integer, DlinkedNode> map = new HashMap<>();
        DlinkedNode head, tail;
        private int capacity;
        private int size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.head = new DlinkedNode();
            this.tail = new DlinkedNode();
            this.head.next = tail;
            this.tail.pre = head;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                move2Head(map.get(key));
                return map.get(key).v;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                removeNode(map.get(key));
                DlinkedNode node = new DlinkedNode(key, value);
                map.put(key, node);
                add2Head(map.get(key));
            } else {
                DlinkedNode node = new DlinkedNode(key, value);
                add2Head(node);
                map.put(key, node);
                size++;
                if (size > capacity) {
                    DlinkedNode ret = removeTail();
                    map.remove(ret.k);
                    size--;
                }
            }
        }

        private void move2Head(DlinkedNode node) {
            removeNode(node);
            add2Head(node);
        }

        private void removeNode(DlinkedNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void add2Head(DlinkedNode node) {
            node.pre = this.head;
            node.next = this.head.next;
            this.head.next.pre = node;
            this.head.next = node;
        }

        private DlinkedNode removeTail() {
            DlinkedNode ret = this.tail.pre;
            removeNode(ret);
            return ret;
        }
    }
}
