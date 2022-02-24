package com.example.greedy;

import java.util.PriorityQueue;

public class Leetcode_295 {
}

class MedianFinder {
    private PriorityQueue<Integer> sQ;
    private PriorityQueue<Integer> bQ;

    /** initialize your data structure here. */
    public MedianFinder() {
        sQ = new PriorityQueue<>();
        bQ = new PriorityQueue<>((x, y) -> y - x);
    }

    public void addNum(int num) {
        if(sQ.isEmpty() || num >= sQ.peek()) {
            sQ.add(num);
        } else {
            bQ.add(num);
        }
        modifySize();
    }

    public double findMedian() {
        if(sQ.size() != bQ.size()) {
            return sQ.size() < bQ.size() ? bQ.peek() : sQ.peek();
        } else {
            if(sQ.size() == 0) {
                return 0.0;
            }
            return (sQ.peek() + bQ.peek()) / 2.0;
        }
    }

    private void modifySize() {
        while (Math.abs(sQ.size() - bQ.size()) > 1) {
            if(sQ.size() < bQ.size()) {
                sQ.add(bQ.poll());
            } else {
                bQ.add(sQ.poll());
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */