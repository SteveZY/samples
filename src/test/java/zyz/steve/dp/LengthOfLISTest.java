package zyz.steve.dp;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LengthOfLISTest {

    @Test
    public void getLenOfLongestIncSubseq() {
        System.out.println(LengthOfLIS.getLenOfLongestIncSubseq(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));

        System.out.println(Arrays.binarySearch(new int[] {1, 3, 5, 6, 7}, 2));
    }
}