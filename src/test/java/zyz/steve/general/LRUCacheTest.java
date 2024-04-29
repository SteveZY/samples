package zyz.steve.general;

import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {
    @Test
    public void testCache(){
        LRUCache cache = new LRUCache(3);
        cache.put(10,10);
        cache.put(20,20);
        cache.put(30,30);

        cache.put(40,40);
//        System.out.println(cache.get(10));
        System.out.println(cache);
    }

}