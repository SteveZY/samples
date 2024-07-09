package zyz.steve.array;

import org.junit.Test;

public class TestJustificaiton2Test {
    @Test
    public void testReflowAndjust(){
        String [] words = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(TextJustificaiton2.fullJustify(words, 16));
    }

}