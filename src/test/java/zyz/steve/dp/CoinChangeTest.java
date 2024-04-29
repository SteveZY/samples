package zyz.steve.dp;

import jdk.nashorn.internal.ir.LiteralNode;
import org.junit.Assert;
import org.junit.Test;
import zyz.steve.Utils;
import zyz.steve.greedy.CanJump;

import javax.swing.plaf.synth.SynthTextAreaUI;

import static org.junit.Assert.*;

public class CoinChangeTest {
    @Test
    public void testCoinChange(){
        int [] coins = {2, 5, 7};
        int ret = CoinChange.coinChange(coins, 10);
        assertEquals(2, ret);

        ret = CoinChange.coinChange(coins,1);
        assertEquals(-1, ret);
    }
    @Test
    public void testUniquePaths(){
        int ret = CoinChange.uniquePaths(3,2);
        assertEquals(3,ret);

        ret = CoinChange.uniquePaths(3,3);
        assertEquals(6,ret);

        System.out.println(CoinChange.uniquePaths(10,30));
    }
    @Test
    public void testUniquePathsOp(){
        long ret = CoinChange.uniquePathsOp(3,2);
        assertEquals(3,ret);

        ret = CoinChange.uniquePathsOp(3,3);
        assertEquals(6,ret);
//
        ret = CoinChange.uniquePathsOp(1000,1);
        assertEquals(1, ret);
    }
    @Test
    public void canJump(){
        int []a = {1,2,3,4,5};

        assertTrue(
                CoinChange.canJump(a)
        );
        a= new int[]{1,0,34,6,7};
        assertFalse(CoinChange.canJump(a));

        a = new int[]{2,0,1,0,4,5};

        assertFalse(CoinChange.canJump(a));
    }
    @Test
    public void testFastestWay(){
        int [][] a = {{7,9,3,4,8,4},{8,5,6,4,5,7}};
        int [][] t = {{2,3,1,3,4},{2,1,2,2,1}};
        int [] e={2,4};
        int [] x= {3,2};
        int n =6;
        CoinChange.fastestWay(a,t,e,x,n);
    }

    @Test
    public void testCutRod(){
        int []p={1,5,8,9,10,17,17,20,24,30,30,34,36,36,37,38,39,44,45,46,46,46,46,46,46,46,46,46,46,46};
        long start = System.currentTimeMillis();
        int ret = CoinChange.cutRodRecursively(p, p.length);
        System.out.println("Used: "+ (System.currentTimeMillis()-start));
        System.out.println(ret);
        start = System.currentTimeMillis();
        ret = CoinChange.cutRodMemo(p, p.length);
        System.out.println("With memo used: "+ (System.currentTimeMillis()-start));

        System.out.println(ret);

        ret = CoinChange.cutRodDPBottomUp(p, p.length);
        System.out.println(ret);

    }
    @Test
    public  void testKnapSackRecursive(){
        int [][] items = {{2,99},{49,82},{9,5},{67,145}};
        long start = System.currentTimeMillis();
        int max = CoinChange.knapsackRecursive(items, 2, 4);
        System.out.println(System.currentTimeMillis()- start);
        System.out.println(max);
    }
    @Test
    public  void testKnapSackRecursiveWithMemo(){
        int [][] items = {{2,1},{3,2},{4,5},{5,6}};
        int max = CoinChange.knapSackMemo(items, 8, 4);
        System.out.println(max);

    }

    @Test
    public void testKnapsackDP(){
        int [][] items = {{2,1},{3,2},{4,5},{5,6}};

        int max = CoinChange.knapSackDP(items, 8);
        System.out.println(max);
    }

    @Test
    public void testKnapSackFull(){
        int [][] items = {{2,1},{3,2},{4,5},{5,6}};
        int max = CoinChange.knapSackFullDP(items, 8);
//        System.out.println(max);
        Assert.assertEquals(10,max);

        items = new int[][] {{2,30}, {3, 50},{4,100},{5,200}};
        max = CoinChange.knapSackFullDP(items,8);
//        System.out.println(max);
        Assert.assertEquals(250,max);
    }
    @Test
    public  void testOnesAndZeros(){
        int [][] items = {{1,1},{3,1},{2,4},{0,1},{1,0}};
        int maxNumOfEle = CoinChange.onesZerosRecursive(items, 5, 3, 5);

        System.out.println(maxNumOfEle);
        items = new int [][]{{1,1},{1,0},{0,1}};
        maxNumOfEle = CoinChange.onesZerosRecursive(items,1,1,3);
        System.out.println(maxNumOfEle);
        String[] strs = {"10", "0001", "111001", "1", "0"};
        items = Utils.fillItems(strs);
        maxNumOfEle = CoinChange.onesZerosRecursive(items,5,3,5);
        System.out.println(maxNumOfEle);
        System.out.println(
                (CoinChange.onesZerosDP(items, 5, 3))
        );
    }

    @Test
    public void basicTest(){
        int[][] items = new int[][] {{1, 1}, {1, 0}, {0, 1}};
        System.out.println(items.length);

    }
}