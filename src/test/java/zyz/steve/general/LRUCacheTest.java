package zyz.steve.general;

import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {
    @Test
    public void testCache(){
        LRUCache cache = new LRUCache(2);
        cache.put(10,10);
        cache.put(20,20);
        cache.get(10);

        cache.put(40,40);
        cache.get(20);
        cache.put(4,4);

        cache.get(10);
        cache.get(40);
        cache.get(4);
//        System.out.println(cache.get(10));
        System.out.println(cache);
    }

}