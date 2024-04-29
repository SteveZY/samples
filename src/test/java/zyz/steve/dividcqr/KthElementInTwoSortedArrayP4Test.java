package zyz.steve.dividcqr;

import org.junit.Test;

import static org.junit.Assert.*;

public class KthElementInTwoSortedArrayP4Test {

    @Test
    public void testFindKthEle(){
        int[] a = {1, 2};
        int[] b = {1, 2};

        System.out.println(KthElementInTwoSortedArrayP4.findKthElement(a, b, 0, 1, 0, 1, 1));
        System.out.println(KthElementInTwoSortedArrayP4.findMedianOptimised(a, b));
    }

}