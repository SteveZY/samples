package zyz.steve.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class KthSmallestTest {

    @Test
    public void findKthSmallest() {
        int[][]m={{-5,2},{3,9}};
        System.out.println(KthSmallest.findKthSmallest(m, 4));

        System.out.println(KthSmallest.findKthSmallestBinarySearch(m, 4));
    }
}