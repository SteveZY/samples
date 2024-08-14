package zyz.steve.array;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ArrayLCCourseTest {
    @Test
    public void testFindPivot(){
        int p = ArrayLCCourse.findPivot(new int[] {4,0});
        System.out.println(p);
    }
    @Test
    public void testDominantIndex(){
        int idx = ArrayLCCourse.dominantIndex(new int[] {5,0,4});
        System.out.println(idx);
    }

    @Test
    public  void testPlusOne(){
        int[] res = ArrayLCCourse.plusOneOptimised(new int[] {9,9});
        for(int digit:res){

            System.out.println(digit);
        }
    }
    @Test
    public void testDiagonalOrder(){
        ArrayLCCourse.findDiagonalOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        ArrayLCCourse.findDiagonalOrderV2(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }
    @Test
    public void testSpiralOrder(){
        int[][] a = {{1, 2, 3}, {4,5, 6}, {7,8,9}};

        ArrayLCCourse.findSpiralOrder(a);
        a = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
//        a[0][0]=1;
        System.out.println("==============");
        System.out.println(ArrayLCCourse.findSpiralOrder(a));

        a = new int[][] {{1, 2},{3,4},{5,6}};
        System.out.println("==============");

        ArrayLCCourse.findSpiralOrder(a);
    }
    @Test
    public void testPascalGenerator(){
        List<List<Integer>> a = ArrayLCCourse.generatePascal(7);

        a.forEach(aa-> {
                    aa.forEach(ee-> {
                        System.out.print(ee);
                        System.out.print(" ");
                    });
                    System.out.println();
                }
        );
    }

    @Test
    public void testGeneratorFromLC(){
        List<List<Integer>> ans = ArrayLCCourse.generatePascalFromLC(3);
        System.out.println(ans);


        ans = ArrayLCCourse.generatePascalMath(5);
        System.out.println(ans);
    }

    @Test
    public void testPascalTriII(){
        List<Integer> ans = ArrayLCCourse.getRowOfPascalTri(1);

        System.out.println(ans);
    }
//  hell World nihao heihei
    @Test
    public void testReverseString(){
        String str = "  hell World nihao heihei  ";
        String ans = ArrayLCCourse.reverseWordsInAString(str);
        System.out.println(ans);
    }
    @Test
    public void testRemoveSpacesWithinString(){
        char[] str = "hell  hei heixxxx".toCharArray();

        int lenAfter = ArrayLCCourse.removeExtraSpaces(str, 0, str.length - 1);
//        System.out.println(lenAfter);
//        System.out.println(String.valueOf(str));
        Assert.assertEquals(16,lenAfter);
        Assert.assertArrayEquals("hell hei heixxxxx".toCharArray(),str);

    }

    @Test
    public void testFindMinNonNeg(){
        Assert.assertEquals(-1,ArrayLCCourse.findMinNonNegative(new int[] {0,0,4}));
        Assert.assertEquals(0,ArrayLCCourse.findMinNonNegative(new int[] {-1,0,4}));
        Assert.assertEquals(1,ArrayLCCourse.findMinNonNegative(new int[] {-1,-1,4}));
        Assert.assertEquals(2,ArrayLCCourse.findMinNonNegative(new int[] {-1,-1,-1}));
    }

    @Test
    public void testSquareSortedArray() {
        int[] a = {-7,-3,0,1, 2};
        int[] ans = ArrayLCCourse.squareSortedArray(a);
        System.out.println(ans);

        ans = ArrayLCCourse.squareSortedArrayII(a);
        Arrays.stream(ans).forEach(e -> {
            System.out.print(e);
            System.out.print(",");
        });
    }
    @Test
    public void testMergeSortedArr(){
        int[] a = {
            1, 2, 3, 0, 0, 0
        };
        int [] b=  {2,5,6};
        ArrayLCCourse.merge(a,3,b,3);
        Arrays.stream(a).forEach(e -> {
            System.out.print(e);
            System.out.print(",");
        });
    }
    @Test
    public void testCheckDblExistence(){
        int[] a = {
                1, 2, 3, 7, 8, 9
        };
        Assert.assertTrue(ArrayLCCourse.checkIfDblExists(a));
        a = new int[]{3,1};
        Assert.assertFalse(ArrayLCCourse.checkIfDblExists(a));

    }

    @Test
    public void testValidMtnArr(){
        int[] a = {
                1, 2, 3, 7, 8, 9,10,0
        };

        System.out.println(ArrayLCCourse.validMountainArr(a));
        a = new int[]{0,1,3,2,1};

        System.out.println(ArrayLCCourse.validMountainArrII(a));
    }
    @Test
    public void testReplaceWithMaxOnRight(){
        int[] a = {
                10
        };
        int[] ans = ArrayLCCourse.replaceEleWithMaxFromRight(a);
        Arrays.stream(ans).forEach(e -> {
            System.out.print(e);
            System.out.print(",");
        });
    }
    @Test
    public void testSortArrayByParity(){
        int[] a = {
                6,  3,5,7,4,2,9
        };
        int[] ans = ArrayLCCourse.sortArrayByParity(a);

        Arrays.stream(ans).forEach(e -> {
            System.out.print(e);
            System.out.print(",");
        });
    }
    @Test
    public void testCountingSort(){
        int[] a = {
                6,  3,5,7,4,2,9,2,1,1,1,2
        };
        ArrayLCCourse.countingSort(a);
        Arrays.stream(a).forEach(e -> {
            System.out.print(e);
            System.out.print(",");
        });
    }
    @Test
    public void test3rdMax(){
        int[] a = {
                3,3,4,4,4
        };
        int ans = ArrayLCCourse.thirdMax(a);
        System.out.println(ans);
    }

    @Test
    public void testFindMissingNums() {
        int[] a = {
                3, 2, 1, 7,1,1,1
        };
        List<Integer> as = ArrayLCCourse.findDisappearedNumbers(a);

        System.out.println(as);
    }

    @Test
    public void testFindDups() {
        int[]a ={3,2,2,3,1,1,5};
        System.out.println(ArrayLCCourse.findDuplicates(a));
    }
    @Test
    public void testMissingNums(){
        System.out.println(ArrayLCCourse.missingNumber(new int[] {0, 3, 1, 4,2}));
    }

    @Test
    public void testBinarySearch(){
        int[] a = new int[] {1, 3, 5, 6, 10};
        System.out.println(Arrays.binarySearch(a, 8));
        System.out.println(ArrayLCCourse.binarySearch(a, 0, 4, 8));
    }
}