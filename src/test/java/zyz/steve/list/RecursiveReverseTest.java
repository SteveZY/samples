package zyz.steve.list;

import org.junit.Test;

public class RecursiveReverseTest {

    @Test
    public void testReverseRecursively() {
        RecursiveReverse.push(20);
        RecursiveReverse.push(13);
        RecursiveReverse.push(9);
        RecursiveReverse.push(8);
        RecursiveReverse.push(6);
        RecursiveReverse.print();
        RecursiveReverse.head =
                RecursiveReverse.reverse(RecursiveReverse.head);
        System.out.println("reversed");
        RecursiveReverse.print();

        System.out.println("reversed the front 2");
        RecursiveReverse.head = RecursiveReverse.reverseN(RecursiveReverse.head,2);


        RecursiveReverse.print();
        System.out.println("reverse the rear 2 and get them to the front");
        RecursiveReverse.head = RecursiveReverse.reverseNFromEnd(RecursiveReverse.head,2);
        RecursiveReverse.print();
        System.out.println("reverse the rear 2");
        RecursiveReverse.counter =0;
        RecursiveReverse.reverseEndN(RecursiveReverse.head,2);
        RecursiveReverse.print();

        //tail recursive
        RecursiveReverse.head = RecursiveReverse.reverseTail(RecursiveReverse.head,null);
        RecursiveReverse.print();
    }

    @Test
    public void testArrays() {
        int[][] xxx = {{11,12},{21,22}};
        System.out.println(xxx[1][1]);
    }
}