package zyz.steve.array;

import org.junit.Test;

import java.util.Arrays;


public class ProductExceptSelfTest {
    @Test
    public void testProductExceptSelf(){
        int[] n = {1,2,3,4};
        System.out.println(Arrays.toString(ProductExceptSelf.productExceptSelf(n)));

        n = new int[] {-1,1,0,-3,3};
        System.out.println(Arrays.toString(ProductExceptSelf.productExceptSelf(n)));

    }

}