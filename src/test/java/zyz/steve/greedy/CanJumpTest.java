package zyz.steve.greedy;

import org.junit.Test;

import static org.junit.Assert.*;

public class CanJumpTest {

    @Test
    public void testCanJump(){
        int []a = {1,2,3,4,5};

        assertTrue(
                CanJump.canJump(a)
        );
        a= new int[]{1,0,34,6,7};
        assertFalse(CanJump.canJump(a));
    }
}