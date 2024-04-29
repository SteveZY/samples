package zyz.steve.sorting;

import org.junit.Assert;
import org.junit.Test;


public class SorttingToSolveTest {

    @Test
    public void testTestMinMeetingRooms(){
        int [][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        Assert.assertEquals(2,SorttingToSolve.minMeetingRooms(intervals));
        intervals = new int[][]{{7,10},{2,4}};
        Assert.assertEquals(1,SorttingToSolve.minMeetingRooms(intervals));

    }

}