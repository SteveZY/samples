import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testXXX(){
//        System.out.println(Main.binarySearchToDetermineMinCost("11000"));//0
//        System.out.println(Main.binarySearchToDetermineMinCost("110001"));//1
//        System.out.println(Main.binarySearchToDetermineMinCost("1100011"));//2
//        System.out.println(Main.binarySearchToDetermineMinCost("11000111"));//2
//        System.out.println(Main.binarySearchToDetermineMinCost("101110110"));//2
        System.out.println(MainClass.binarySearchToDetermineMinCost("1001001001001"));//3
//        System.out.println(Main.binarySearchToDetermineMinCost("0000111111"));//2
        System.out.println(MainClass.binarySearchToDetermineMinCost("00000"));//2
        System.out.println(MainClass.binarySearchToDetermineMinCost("1111"));//2
    }
    @Test
    public void testBeautifulArray(){
        assertEquals(4, MainClass.beautifulArray(new int[] {3, 5, 4, 6}));
        assertEquals(5, MainClass.beautifulArray(new int[] {5 ,8 ,5 ,5}));
        assertEquals(6, MainClass.beautifulArray(new int[] {6, 6, 6, 6}));
        assertEquals(13, MainClass.beautifulArray(new int[] {3, 3 ,10, 12}));
        assertEquals(0, MainClass.beautifulArray(new int[] {0,0,0,0}));
    }

    @Test
    public void testXX(){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.abs(Integer.MIN_VALUE));
    }

}