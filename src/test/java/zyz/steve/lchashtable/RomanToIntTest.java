package zyz.steve.lchashtable;

import org.junit.Test;
import zyz.steve.array.ProductExceptSelf;
import zyz.steve.array.ProductExceptSelfTest;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RomanToIntTest {
    @Test
    public void testRomanToInt(){
        System.out.println(RomanToInt.romanToInt("LIIX"));
    }
    @Test
    public void testMaxSubArray(){
        System.out.println(RomanToInt.maxSubArray(new int[]{-1,-2,-3,-4}));
    }

    @Test
    public void testMergeIntv(){
        int [][] ii = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(ProductExceptSelf.mergeIntervals(ii)));
    }

    @Test
    public void testMaxProfit(){
        int [] a ={7,1,5,3,6,4};
        System.out.println(ProductExceptSelf.maxProfit(a));
        a = new int[]{7,6,4,3,1};
        System.out.println(ProductExceptSelf.maxProfit(a));

    }
}