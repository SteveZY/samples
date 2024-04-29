package zyz.steve.priorityq;

import org.junit.Test;

import static org.junit.Assert.*;

public class KaryHeapTest {
    int [] a={4,10,3,20,1,2};
    @Test
    public void testKaryHeap(){
        KaryHeap h = new KaryHeap(a, 3);
        h.buildHeap();
        System.out.println(h);
    }

}