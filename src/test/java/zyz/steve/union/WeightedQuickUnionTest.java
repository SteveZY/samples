package zyz.steve.union;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeightedQuickUnionTest {
    private final WeightedQuickUnion wqunion = new WeightedQuickUnion(10);
    @Test
    public  void testUnion(){
        wqunion.union(2,3);
        wqunion.union(5,3);
        wqunion.union(6,5);
        wqunion.union(5,4);
        wqunion.union(4,6);
        wqunion.union(8,0);
        wqunion.union(1,9);
        wqunion.union(6,9);



        Assert.assertTrue(wqunion.connected(1,6));
    }

}