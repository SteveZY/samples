package zyz.steve.general;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class PowerOf3Test {
    @Test
    public void testIsPowerOf3(){
        assertFalse(PowerOf3.isPowerOf3(45));
        assertFalse(PowerOf3.isPowerOf3(0));
        assertFalse(PowerOf3.isPowerOf3(12));
        assertTrue(PowerOf3.isPowerOf3(9));
    }
}