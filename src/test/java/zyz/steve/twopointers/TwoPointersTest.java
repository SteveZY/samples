package zyz.steve.twopointers;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TwoPointersTest {
    @Test
    public void testReverseString(){
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        TwoPointers.reverseString(s);
    }

    @Test
    public void testCostOfRemovalOfZeros(){
        int cost = TwoPointers.costOfRemovalZeros("100000001");

        System.out.println(cost);
    }
    @Test
    public void testWaterContainer(){
        int [] arr = {1,8,6,2,5,4,8,3,7};

        Assert.assertEquals(49,TwoPointers.getMostWater2Contain(arr));
    }
    @Test
    public void testMoveZeros(){
        int [] arr={0,1,0,3,12};
        TwoPointers.moveZeros(arr);
        Assert.assertArrayEquals(new int[]{1,3,12,0,0},arr);
    }
    @Test
    public void testTwoSumII(){
        int [] a = {1,2,6,7,8,9};
        int[] ret = TwoPointers.twoSumII(a, 16);
        Arrays.stream(ret).forEach(System.out::println);

//        ret = TwoPointers.twoSum(a,30); // this version has a bug
//
//        Arrays.stream(ret).forEach(System.out::println);

    }
    @Test
    public void testCountOnes(){
        int [] a ={0,1,0,1,1,1};
//        System.out.println(TwoPointers.countOnes(a));

        System.out.println(TwoPointers.countOnesWithPointers(a));
    }
    @Test
    public void testReverseArray(){
        int [] a = {1,2,6,7,8,9};
        TwoPointers.reverseArray(a,3,5);
        Arrays.stream(a).forEach(System.out::println);
    }
    @Test
    public void testRotateArray(){
        int [] a = {1,2,6,7,8,9};
        TwoPointers.rotateArray(a,5);
        Arrays.stream(a).forEach(System.out::println);

    }

    @Test
    public void testRemoveDups() {
        int [] a = {2,2,2,2,2,3,4,4,4,5,6,6,6};

        int r = TwoPointers.removeDupsFromSortedArr(a);

        System.out.println(r);
        Arrays.stream(a).forEach(System.out::println);
    }
}