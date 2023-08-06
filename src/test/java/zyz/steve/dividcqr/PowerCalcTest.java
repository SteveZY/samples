package zyz.steve.dividcqr;

import org.junit.Assert;
import org.junit.Test;

public class PowerCalcTest {
    @Test
    public void testQPower(){
        int res = PowerCalc.qpowerRecursively(8, 3);
        Assert.assertEquals(512,res);

        res = PowerCalc.qpowerRecursively(8, 0);
        Assert.assertEquals(1,res);
    }
    @Test
    public void testFib(){
        int[][] m = {{1,1},{1,0}};
        int[][] res = PowerCalc.qpower(m, 5);
        System.out.println(res[0][0]);
        Assert.assertEquals(8,res[0][0]);//F6 == 8
        //        m = {{1,1},{1,0}};
        res = PowerCalc.qpower(m, 10);
        System.out.println(res[0][0]);

    }
}