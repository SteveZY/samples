package zyz.steve.dividcqr;

import org.junit.Test;


public class KthElementInTwoSortedArrayP4Test {

    @Test
    public void testFindKthEle(){
        int[] a = {1, 2,11,34};
        int[] b = {3, 4,5,6,9};

        System.out.println(KthElementInTwoSortedArrayP4.findKthElement(a, b, 0, 1, 0, 4, 3));
        System.out.println(KthElementInTwoSortedArrayP4.findMedianOptimised(a, b));
    }

}