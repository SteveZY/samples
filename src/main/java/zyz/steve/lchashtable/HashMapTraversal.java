package zyz.steve.lchashtable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HashMap 遍历的几种常见方法
 *
 * 1. entrySet() - 最高效（推荐用于需要键值对都使用的情况）
 * 2. keySet() - 当只需要键时使用
 * 3. values() - 当只需要值时使用
 * 4. Iterator - 传统的迭代方法
 * 5. forEach - Lambda 表达式（Java 8+）
 */
public class HashMapTraversal {

    public static void main(String[] args) {
        // 创建一个 HashMap
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Cherry", 15);
        map.put("Date", 25);

        System.out.println("===== HashMap 遍历方法演示 =====\n");

        // 方法 1: 使用 entrySet() 遍历 - 最推荐
        System.out.println("方法 1: entrySet() 遍历（最高效）");
        traverseUsingEntrySet(map);

        // 方法 2: 使用 keySet() 遍历
        System.out.println("\n方法 2: keySet() 遍历");
        traverseUsingKeySet(map);

        // 方法 3: 使用 values() 遍历
        System.out.println("\n方法 3: values() 遍历");
        traverseUsingValues(map);

        // 方法 4: 使用 Iterator 遍历
        System.out.println("\n方法 4: Iterator 遍历");
        traverseUsingIterator(map);

        // 方法 5: 使用 forEach Lambda 表达式
        System.out.println("\n方法 5: forEach Lambda 遍历");
        traverseUsingForEach(map);

        // 方法 6: 使用 values().stream()
        System.out.println("\n方法 6: Stream API 遍历");
        traverseUsingStream(map);
    }

    /**
     * 方法 1: 使用 entrySet() 遍历
     * 优点: 性能最好，避免键值的二次查询
     * 推荐: 当需要同时使用键和值时，优先使用此方法
     */
    public static void traverseUsingEntrySet(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("  " + key + " -> " + value);
        }
    }

    /**
     * 方法 2: 使用 keySet() 遍历
     * 优点: 当只需要键时，使用此方法
     * 注意: 如果需要值，会进行额外的 map.get(key) 查询
     */
    public static void traverseUsingKeySet(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println("  " + key + " -> " + value);
        }
    }

    /**
     * 方法 3: 使用 values() 遍历
     * 优点: 当只需要值时，使用此方法
     * 用途: 不需要键，只需要所有的值
     */
    public static void traverseUsingValues(Map<String, Integer> map) {
        for (Integer value : map.values()) {
            System.out.println("  值: " + value);
        }
    }

    /**
     * 方法 4: 使用 Iterator 遍历
     * 优点: 可以在遍历时安全地删除元素
     * 用途: 需要在循环中删除某些条目时使用
     */
    public static void traverseUsingIterator(Map<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
            // 如果需要删除: iterator.remove();
        }
    }

    /**
     * 方法 5: 使用 forEach Lambda 表达式
     * 优点: 代码简洁清晰，现代Java风格
     * 用途: Java 8+
     */
    public static void traverseUsingForEach(Map<String, Integer> map) {
        map.forEach((key, value) -> {
            System.out.println("  " + key + " -> " + value);
        });
    }

    /**
     * 方法 6: 使用 Stream API 遍历
     * 优点: 函数式编程风格，可以链式操作
     * 用途: 需要进行过滤、映射等操作时
     */
    public static void traverseUsingStream(Map<String, Integer> map) {
        map.entrySet().stream()
                .forEach(entry -> System.out.println("  " + entry.getKey() + " -> " + entry.getValue()));
    }

    /**
     * 实际应用示例: 找出所有值大于等于 15 的键值对
     */
    public static void exampleFilterValues(Map<String, Integer> map) {
        System.out.println("\n===== 实际应用示例 =====");
        System.out.println("找出所有值 >= 15 的键值对:");
        
        // 使用 entrySet 和 stream
        map.entrySet().stream()
                .filter(entry -> entry.getValue() >= 15)
                .forEach(entry -> System.out.println("  " + entry.getKey() + " -> " + entry.getValue()));
    }

    /**
     * 实际应用示例: 安全删除
     */
    public static void exampleSafeDelete(Map<String, Integer> map) {
        System.out.println("\n删除所有值小于 20 的条目:");
        // 创建副本以防止 ConcurrentModificationException
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 20) {
                iterator.remove();
                System.out.println("  已删除: " + entry.getKey());
            }
        }
    }
}
