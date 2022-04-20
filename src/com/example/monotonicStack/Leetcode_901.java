package com.example.monotonicStack;

import java.util.Stack;

public class Leetcode_901 {
    class StockSpanner {
        Stack<Integer> prices, gains;

        public StockSpanner() {
            prices = new Stack<>();
            gains = new Stack<>();
        }

        // [10, 3, 9, 3, 9] prices: [10] -> [10, 3] -> [10, 9] -> [10, 9, 3] -> [10. 9]
        //                  gains: [1] -> [1, 1] -> [1, 2] -> [1, 2, 1] -> [1, 4]
        // [(10, 1)] -> [(10, 1), (3, 1)] -> [(10, 1), (9, 2)] -> [(10, 1), (9, 2), (3, 1)] -> [(10, 1), (9, 4)]
        public int next(int price) {
            int gain = 1;
            while(!prices.isEmpty() && price >= prices.peek()) {
                gain += gains.pop();
                prices.pop();
            }
            prices.push(price);
            gains.push(gain);
            return gain;
        }
    }
    /**
     * Your StockSpanner object will be instantiated and called as such:
     * StockSpanner obj = new StockSpanner();
     * int param_1 = obj.next(price);
     */
}
