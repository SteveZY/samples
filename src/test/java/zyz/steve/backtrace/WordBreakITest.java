package zyz.steve.backtrace;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordBreakITest {

    @Test
    public void wordBreak() {
        ArrayList<String> wordDict = new ArrayList<>();

        wordDict.add("a");
        wordDict.add("aa");
//          {"leet", "code"};
        System.out.println(WordBreakI.wordBreak("aaab", wordDict));
    }
}