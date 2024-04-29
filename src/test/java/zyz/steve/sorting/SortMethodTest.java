package zyz.steve.sorting;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortMethodTest {
    int[] a = {-94,5,67,8,3,5,2,9};

    @Test
    public void testMergeSort(){
        int[] b = new int[a.length];
        SortMethod.mergeSort(a,b,0, a.length -1);
        System.out.println(b);
    }
    @Test
    public void testQuickSort(){
        SortMethod.quickSort(a,0,a.length-1);
    }

    @Test
    public void testCountingSort(){
        SortMethod.countingSort(a);
        for(int e : a){
            System.out.println(e);
        }
    }

}