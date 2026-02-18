package zyz.steve.lchashtable;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * HashMap 遍历方法的单元测试
 */
public class HashMapTraversalTest {

    private Map<String, Integer> testMap;

    @Before
    public void setUp() {
        testMap = new HashMap<>();
        testMap.put("Apple", 10);
        testMap.put("Banana", 20);
        testMap.put("Cherry", 15);
        testMap.put("Date", 25);
    }

    /**
     * 测试 entrySet() 遍历是否获取所有键值对
     */
    @Test
    public void testTraverseWithEntrySet() {
        int count = 0;
        for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
            count++;
        }
        assertEquals(4, count);
    }

    /**
     * 测试 keySet() 遍历是否获取所有键
     */
    @Test
    public void testTraverseWithKeySet() {
        int count = 0;
        for (String key : testMap.keySet()) {
            assertNotNull(key);
            assertTrue(testMap.containsKey(key));
            count++;
        }
        assertEquals(4, count);
    }

    /**
     * 测试 values() 遍历是否获取所有值
     */
    @Test
    public void testTraverseWithValues() {
        int count = 0;
        int sum = 0;
        for (Integer value : testMap.values()) {
            assertNotNull(value);
            sum += value;
            count++;
        }
        assertEquals(4, count);
        assertEquals(70, sum); // 10 + 20 + 15 + 25 = 70
    }

    /**
     * 测试使用 Iterator 遍历
     */
    @Test
    public void testTraverseWithIterator() {
        Iterator<Map.Entry<String, Integer>> iterator = testMap.entrySet().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
            count++;
        }
        assertEquals(4, count);
    }

    /**
     * 测试使用 forEach 遍历
     */
    @Test
    public void testTraverseWithForEach() {
        int count = 0;
        for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
            count++;
        }
        assertEquals(4, count);
    }

    /**
     * 测试使用 Stream API 遍历
     */
    @Test
    public void testTraverseWithStream() {
        long count = testMap.entrySet().stream().count();
        assertEquals(4, count);
    }

    /**
     * 测试安全删除 - 使用 Iterator
     */
    @Test
    public void testSafeDeleteWithIterator() {
        Iterator<Map.Entry<String, Integer>> iterator = testMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            // 删除所有值小于 20 的条目
            if (entry.getValue() < 20) {
                iterator.remove();
            }
        }
        assertEquals(2, testMap.size());
        assertTrue(testMap.containsKey("Banana"));
        assertTrue(testMap.containsKey("Date"));
        assertFalse(testMap.containsKey("Apple"));
        assertFalse(testMap.containsKey("Cherry"));
    }

    /**
     * 测试计算所有值的总和
     */
    @Test
    public void testCalculateSum() {
        int sum = 0;
        for (Integer value : testMap.values()) {
            sum += value;
        }
        assertEquals(70, sum);
    }

    /**
     * 测试计算所有值的平均值
     */
    @Test
    public void testCalculateAverage() {
        int sum = 0;
        for (Integer value : testMap.values()) {
            sum += value;
        }
        double average = (double) sum / testMap.size();
        assertEquals(17.5, average, 0.01);
    }

    /**
     * 测试找出最大值和对应的键
     */
    @Test
    public void testFindMaxValueAndKey() {
        String maxKey = null;
        Integer maxValue = Integer.MIN_VALUE;
        
        for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        
        assertEquals("Date", maxKey);
        assertEquals(25, (int) maxValue);
    }

    /**
     * 测试找出最小值和对应的键
     */
    @Test
    public void testFindMinValueAndKey() {
        String minKey = null;
        Integer minValue = Integer.MAX_VALUE;
        
        for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
            if (entry.getValue() < minValue) {
                minValue = entry.getValue();
                minKey = entry.getKey();
            }
        }
        
        assertEquals("Apple", minKey);
        assertEquals(10, (int) minValue);
    }

    /**
     * 测试过滤 - 找出所有值大于等于 15 的键值对
     */
    @Test
    public void testFilterValues() {
        int count = 0;
        for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
            if (entry.getValue() >= 15) {
                count++;
            }
        }
        assertEquals(3, count); // Banana(20), Cherry(15), Date(25)
    }

    /**
     * 测试使用 Stream 进行过滤
     */
    @Test
    public void testFilterWithStream() {
        long count = testMap.entrySet().stream()
                .filter(entry -> entry.getValue() >= 15)
                .count();
        assertEquals(3, count);
    }

    /**
     * 测试检查是否存在特定的值
     */
    @Test
    public void testContainsValue() {
        boolean hasValue = false;
        for (Integer value : testMap.values()) {
            if (value == 20) {
                hasValue = true;
                break;
            }
        }
        assertTrue(hasValue);
    }

    /**
     * 测试性能对比：entrySet vs keySet
     * entrySet 避免了额外的 map.get() 查询
     */
    @Test
    public void testPerformanceEntrySetVsKeySet() {
        long startTime, endTime;
        
        // entrySet 方法
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
            }
        }
        endTime = System.nanoTime();
        long entrySetTime = endTime - startTime;
        
        // keySet 方法
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            for (String key : testMap.keySet()) {
                Integer value = testMap.get(key);
            }
        }
        endTime = System.nanoTime();
        long keySetTime = endTime - startTime;
        
        // entrySet 通常会快一些
        System.out.println("entrySet 耗时: " + entrySetTime + "ns");
        System.out.println("keySet 耗时: " + keySetTime + "ns");
    }
}
