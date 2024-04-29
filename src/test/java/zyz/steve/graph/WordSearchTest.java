package zyz.steve.graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordSearchTest {
    @Test
    public void testWordExist(){
        char[][] b = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String s = "ASFC";
        System.out.println(WordSearch.exist(b, s));
    }

}