package com.example.trie;

/**
 * 字典树
 */
public class Leetcode_208 {
    class Trie {

        Trie[] children = new Trie[26];
        boolean isEnd;

        public Trie() {

        }

        public void insert(String word) {
            Trie t = this;
            for(int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if(t.children[idx] == null) {
                    t.children[idx] = new Trie();
                }
                t = t.children[idx];
            }
            t.isEnd = true;
        }

        private Trie searchPrefix(String word) {
            Trie t = this;
            for(int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if(t.children[idx] == null) {
                    return null;
                }
                t = t.children[idx];
            }
            return t;
        }

        public boolean search(String word) {
            Trie t = searchPrefix(word);
            return t != null && t.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
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
