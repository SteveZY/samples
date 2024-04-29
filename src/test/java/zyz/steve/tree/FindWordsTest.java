package zyz.steve.tree;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindWordsTest {

    @Test
    public void testFindWords() {
//        char[][] b={{'o','a','b','n'},{'o','t','a','e'},{'a','h','k','r'},{'a','f','l','v'}};
        char[][] b={{'a','a'}};
        String[] words = {"a"};
        FindWords f = new FindWords();
        List<String> ret = f.findWords(b, words);
        System.out.println(ret);
    }
}