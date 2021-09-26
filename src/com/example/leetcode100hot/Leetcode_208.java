package com.example.leetcode100hot;

/**
 * 前缀树
 */
public class Leetcode_208 {
    class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for(int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if(node.children[idx] == null) {
                    node.children[idx] = new Trie();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String word) {
            return searchPrefix(word) != null;
        }

        private Trie searchPrefix(String word) {
            Trie node = this;
            for(int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if(node.children[idx] == null) {
                    return null;
                }
                node = node.children[idx];
            }
            return node;
        }
    }
    public static void main(String[] args) {
        Trie trie = new Leetcode_208().new Trie();
        trie.insert("app");
        trie.insert("bpple");
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        System.out.println(trie.search("bpp"));
    }
}
