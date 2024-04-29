package zyz.steve.general;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;


public class ShortestUnsortedSubTest {
    @Test
    public void testWoStack(){
        int [] a = {2,3,5,4,7,6,8,9};
        int len = ShortestUnsortedSub.findSWoStack(a);
        System.out.println(len);
    }

    @Test
    public void testIsSorted() {
        int[] a = {1, 2, 3};
        int sorted = ShortestUnsortedSub.isSorted(a);
        assertEquals(1, sorted);
        a = new int[] {1, 2, 3, 1};
        sorted = ShortestUnsortedSub.isSorted(a);
        assertEquals(0, sorted);
        a = new int[] {3, 2, 1};
        sorted = ShortestUnsortedSub.isSorted(a);
        assertEquals(-1, sorted);
    }
}