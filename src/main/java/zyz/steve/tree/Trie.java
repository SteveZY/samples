package zyz.steve.tree;

//前缀树 LC#208
//https://leetcode.com/problems/implement-trie-prefix-tree/submissions/1205966320/

/**
 * 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode(null);
    }
    public void insert(String s){
//        TrieNode curNode = root;
        insertRecursively(root,s);

    }
    public void insertIteratively(String s){
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(node.children[c-'a'] == null){
                node.children[c-'a'] = new TrieNode(c);
            }
            node = node.children[c-'a'];
        }
        node.end=true;
    }

    public boolean search(String s){
        return search(root,s);
    }
    public boolean startsWith(String s){
        return startsWith(root, s);

    }
    public void delete(String s){
        delete(root, s);
    }

    private void delete(TrieNode node, String s) {
        if(node==null) return;
        if(s==null || s.length()==0){
            if(node.end) node.end=false;
            return;
        }
        delete(node.children[s.charAt(0)-'a'],s.substring(1));
    }

    private boolean search(TrieNode node, String s){
        if(node==null) return false;
        if (s.length() == 0) {
            return node.end;
        }
        char cur = s.charAt(0);
        return search(node.children[cur - 'a'],s.substring(1));
    }
    private boolean startsWith(TrieNode node , String s){
        if(node == null) {
            return false;
        }

        if(s.length() == 0 ) return true;
        return startsWith(node.children[s.charAt(0)-'a'],s.substring(1));
    }
    private void insertRecursively(TrieNode node, String s){

        boolean end = false;
        if(s.length()==1) end =true;
        char cur = s.charAt(0);
        if(node.children[cur - 'a']== null){
            node.children[cur - 'a'] = new TrieNode(cur);
        }
        if(end) {
            node.children[cur - 'a'].end=end;
            return;
        }
        insertRecursively(node.children[cur - 'a'] ,s.substring(1));
    }
    class TrieNode{
        Character data;
        boolean  end;

        TrieNode [] children;
        TrieNode(Character c){
            data = c;
            end = false;
            children = new TrieNode[26];
        }
    }
}
