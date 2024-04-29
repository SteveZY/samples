package zyz.steve.dp;

import org.junit.Test;

import static org.junit.Assert.*;

public class HouseRobberTest {

    @Test
    public void robberMoney() {
        System.out.println(HouseRobber.robberMoney(new int[] {2, 1, 1, 2}));
        System.out.println(HouseRobber.robberMoney(new int[] {1,2,3,1}));
        System.out.println(HouseRobber.robberMoney(new int[] {2,7,3,9,1}));

//dp
        System.out.println(HouseRobber.robDp(new int[] {2, 1, 1, 2}));
        System.out.println(HouseRobber.robDp(new int[] {1,2,3,1}));
        System.out.println(HouseRobber.robDp(new int[] {2,7,3,9,1}));
        System.out.println(HouseRobber.robDp(new int[] {0}));
        System.out.println(HouseRobber.robDp(new int[] {0,1}));
        System.out.println(HouseRobber.robDp(new int[] {0,1,3}));
        System.out.println(HouseRobber.robDp(new int[] {2,1}));
    }
}