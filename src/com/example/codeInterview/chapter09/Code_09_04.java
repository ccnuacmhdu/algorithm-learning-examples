package com.example.codeInterview.chapter09;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code_09_04 {
    // 样本进来会包一层，叫做元素，用户给的是V
    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }

    }

    public static class UnionFindSet<V> {
        public HashMap<V, Element<V>> elementMap;	// 用户的每个V封装成一个Element
        // key  某个元素  value 该元素的父
        public HashMap<Element<V>, Element<V>> fatherMap;	// 元素和父元素
        // key 某个集合的代表元素   value 该集合的大小
        public HashMap<Element<V>, Integer> sizeMap;	// 每个集合根和该集合大小

        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Element<V> element = new Element<V>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        // 给定一个ele，往上一直找，把代表元素返回，一路往上找的所有元素的父亲全都改成根
        private Element<V> findHead(Element<V> element) {
            Stack<Element<V>> path = new Stack<>();
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF) {
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }

    }

}
