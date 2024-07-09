package zyz.steve.lchashtable;

import org.junit.Test;
import zyz.steve.array.ProductExceptSelf;
import zyz.steve.array.ProductExceptSelfTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RomanToIntTest {

    @Test
    public void testRomanToInt() {
        System.out.println(RomanToInt.romanToInt("LIIX"));
    }

    @Test
    public void testMaxSubArray() {
        System.out.println(RomanToInt.maxSubArray(new int[] {-1, -2, -3, -4}));
    }

    @Test
    public void testMergeIntv() {
        int[][] ii = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(ProductExceptSelf.mergeIntervals(ii)));
    }

    @Test
    public void testMaxProfit() {
        int[] a = {7, 1, 5, 3, 6, 4};
        System.out.println(ProductExceptSelf.maxProfit(a));
        System.out.println(ProductExceptSelf.profitdp(a));
        a = new int[] {7, 6, 4, 3, 1};
        System.out.println(ProductExceptSelf.maxProfit(a));
        System.out.println(ProductExceptSelf.profitdp(a));
    }

    @Test
    public void testNumPairs() {
        int[] aa = new int[] {1, 3, 4};
        System.out.println(numberOfPairs(aa, aa, 1));
    }

//    https://leetcode.com/problems/find-the-number-of-good-pairs-ii/description/
    // #3164
    private long numberOfPairs(int[] n1, int[] n2, int k) {

        Map<Integer, Integer> fm = new HashMap<>();

        for (int i = 0; i < n1.length; i++) {
            for (int j = 1; j * j <= n1[i]; j++) {
                if (n1[i] % j == 0) {
                    fm.put(j, fm.getOrDefault(j, 0) + 1);
                    int af = n1[i] / j;
                    if (af != j) {
                        fm.put(af, fm.getOrDefault(af, 0) + 1);
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < n2.length; i++) {
            ans += fm.getOrDefault(n2[i] * k, 0);
        }
        return ans;
    }

}