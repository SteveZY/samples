package zyz.steve.fib;

import org.junit.Assert;
import org.junit.Test;
import zyz.steve.dividcqr.PowerCalc;

public class FibTest {
    @Test
    public void testFibWithMem(){
        long f5 = Fib.fWithMem(5);
        Assert.assertEquals(5,f5);
        long start = System.currentTimeMillis();
        System.out.println(Fib.fWithMem(50));
        System.out.println("time used:"+ (System.currentTimeMillis()-start)+"ms");
    }
    @Test
    public void testFibTailRecur(){
        System.out.println(Fib.fibTailRecursive(3,1,1));
    }

    @Test
    public void fibWithMatricMulti(){
        int[][] fibMatricFactor = new int[][] {{1, 1}, {1, 0}};
        int[][] r = PowerCalc.qpower(fibMatricFactor, 30);

        System.out.println(r[0][0]);

    }

}