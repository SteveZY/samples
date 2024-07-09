package com.fluentretail.graphql;

import org.junit.Test;


public class MergeTest {
    @Test
    public void testMergeSort(){
        int[] a = new int[] {2,1,5, 4};//{8, 6, 5, 4, 3, 2,7};
        Merge.msort(a);

        for(int e:a ){
            System.out.println(e);
        }
    }

    @Test
    public void testQsort(){
        int[] d = new int[] {3,2};
        Merge.qsort(d);
        for(int e:d ){
            System.out.println(e);
        }
    }

}