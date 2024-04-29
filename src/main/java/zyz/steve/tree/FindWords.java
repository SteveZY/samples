package zyz.steve.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWords {
    private final static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    //https://leetcode.com/problems/word-search-ii/description/
    public List<String> findWords(char[][] b, String[] words) {
        Set<String> ret = new HashSet<>();
        Trie trie = new Trie();
        for (String s : words) {
            trie.insert(s);
        }
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                dfs(b, i, j, trie.root, ret);
            }
        }

        return new ArrayList<>(ret);
    }

    private void dfs(char[][] b, int r, int c, TrieNode node, Set<String> ret) {
        if (node == null) {
            return;
        }
        int rows = b.length;
        int cols = b[0].length;
        if (!inArea(rows, cols, c, r)) {
            return;
        }
        char ch = b[r][c];
        if (ch == '#') {
            return; //visited already
        }
        TrieNode child = node.children[ch - 'a'];
        if(child!=null) {
            b[r][c] = '#';//mark as visited
            if(!child.word.isEmpty())ret.add(child.word);
            for (int[] ints : dir) {

                dfs(b, r + ints[0], c + ints[1], child, ret);
            }
            b[r][c] = ch;
        }
    }

    private boolean inArea(int rs, int cs, int c, int r) {
        return c >= 0 && c < cs && r >= 0 && r < rs;
    }

    class Trie {

        private final TrieNode root;

        public Trie() {
            root = new TrieNode(null);
        }

        //Iteratively
        public void insert(String s) {
            TrieNode node = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode(c);
                }
                node = node.children[c - 'a'];
            }
            node.word = s;
        }
    }

    class TrieNode {

        Character data;

        String word;

        TrieNode[] children;

        TrieNode(Character c) {
            data = c;
            word = "";
            children = new TrieNode[26];
        }
    }
}
