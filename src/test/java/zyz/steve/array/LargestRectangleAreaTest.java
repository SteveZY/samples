package zyz.steve.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class LargestRectangleAreaTest {

    @Test
    public void largestRectangleAreaWithStack() {
        int[] h = {1, 0, 1, 0, 1};
        System.out.println(LargestRectangleArea.twoLoops(h));
    }
}