package zyz.steve.priorityq;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HeapImplTest {
    @Test
    public  void testheapInit(){
        int [] a={4,10,3,20,1,2};
        HeapImpl h = new HeapImpl(a);
        Arrays.stream(a).forEach(System.out::println);
        System.out.println(a);

//        int [] b = {4,10,3,20,1,2};
//        h.heapifyCopied(b,0);
//        System.out.println(b);
        h.sort();
        Arrays.stream(a).forEach(System.out::println);

    }

}