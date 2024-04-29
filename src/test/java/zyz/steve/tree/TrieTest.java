package zyz.steve.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {


    @Test
    public void insert() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        System.out.println(trie);
    }
    @Test
    public void testSearch(){
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
//        System.out.println("app".substring(4));
        //        System.out.println(trie.search("a"));
        System.out.println(trie.search("aa"));
        System.out.println(trie.search("ap"));
        System.out.println(trie.search("appl"));
        System.out.println(trie.search("apple"));
    }
    @Test
    public void testStartsWith(){
        Trie trie = new Trie();
//        trie.insert("apple");
//        trie.insert("app");
        System.out.println(trie.startsWith("a"));
        System.out.println(trie.startsWith("app"));
        System.out.println(trie.startsWith("applee"));
    }
    @Test
    public void testDelete(){
        Trie trie = new Trie();
        trie.delete("a");
        trie.delete(null);
        trie.insert("apple");
        trie.insert("app");

        trie.delete("app");
        System.out.println(trie);

        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        assertFalse(trie.startsWith("appa"));
    }
}