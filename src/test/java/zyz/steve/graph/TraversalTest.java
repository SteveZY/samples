package zyz.steve.graph;

import org.junit.Assert;
import org.junit.Test;

import javax.management.remote.TargetedNotification;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TraversalTest {
    @Test
    public void testArrayGenerated(){
        System.out.println(Arrays.deepToString(Traversal.graph));
    }
    @Test
    public void testContains(){
        int[] a ={1,3,5,6,7,8};
        int[] b = {0};
        Assert.assertFalse(
                Traversal.findFromArray(a,9,0,5)
        );
        Assert.assertTrue(
                Traversal.findFromArray(a,1,0,5)
        );
        Assert.assertTrue(
                Traversal.findFromArray(a,8,0,5)
        );
        Assert.assertTrue(
                Traversal.findFromArray(a,5,0,5)
        );

        Assert.assertFalse(
                Traversal.findFromArray(a,0,0,5)
        );

        Assert.assertFalse(
                Traversal.findFromArray(b,8,0,0)
        );
        Assert.assertTrue(
                Traversal.findFromArray(b,0,0,0)
        );
    }
    @Test
    public void testDfsPreorder(){
        System.out.println("recursively");
        Traversal.dfsRecurisivelyPre(Traversal.graph, 0);

        System.out.println("iterating");
        Traversal.resetMarker();
        Traversal.dfsIterPreorder(Traversal.graph, 0);
    }
    @Test
    public void testDfsPostorder(){
        System.out.println("recursively");
        Traversal.dfsRecurisivelyPost(Traversal.graph, 0);

//        System.out.println("iterating");
//        Traversal.resetMarker();
//        Traversal.dfsIterPostorder(Traversal.graph, 0);
    }
    @Test
    public void testNumOfMins(){
        System.out.println(Traversal.numOfMins(6, 0, new int[] {1,2,-1,1,3,4}, new int[] {0,1,1,1,1,0}));
    }
}