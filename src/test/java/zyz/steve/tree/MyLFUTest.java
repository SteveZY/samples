package zyz.steve.tree;

import org.junit.Test;

public class MyLFUTest {
    @Test
    public void testLFU(){

        MyLFU lfu = new MyLFU(2);

        lfu.put(1,1);
        lfu.put(2,2);
        lfu.get(1);
        lfu.get(1);
        lfu.get(1);
        lfu.put(3,3);
        lfu.get(2);
        lfu.get(3);
        lfu.put(4,4);
        lfu.get(1);
        lfu.get(3);
        lfu.get(4);
    }

}