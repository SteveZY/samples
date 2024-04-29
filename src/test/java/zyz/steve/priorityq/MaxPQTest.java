package zyz.steve.priorityq;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MaxPQTest {
    MaxPQ mxpq;
    @Before
    public void setup(){
        mxpq = new MaxPQ(5);
//        {9,8,4,5,6,7,3,2,11,45}
        Stream.of(9,8,4,5,6,7,3,2,11,45).forEach(e -> mxpq.insert(e));

    }

    @Test
    public void testMaxPQDelMax() {
        int max = mxpq.delMax();

        assertEquals(45, max);
        max = mxpq.delMax();
        assertEquals(11, max);
    }

}