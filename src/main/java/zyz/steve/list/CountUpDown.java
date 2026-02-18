package zyz.steve.list;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import zyz.steve.twopointers.IntervalSolutions;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// 练习 O（1） data structure·
// 记录 thumb up or down for given ID
public class CountUpDown {
    // 先用 map 的方案， 非 O（1）
    Map<Integer, Integer> idToCounter = new HashMap<>();
    TreeMap<Integer, Set<Integer>> countToIds = new TreeMap<>();

    private void updateCache(Integer id, int type) {
        // save cur count and remove the id temporarily before updating
        Integer count = idToCounter.get(id);
        idToCounter.remove(id);

        if (null == count) count = 0; // 不存在， 设初值为0
        // 从 cur count set 中移除， 若存在
        countToIds.compute(count, (k, v) -> {
            if (null != v) {
                v.remove(id);
                // 当一个元素都没有的时候，就remove 整个记录
                if (v.isEmpty()) return null;
            }
            return v;
        });

        // update count by 1
        int newCount = count + type;
        if (newCount > 0) {
            // when new count greater than 0, update cache
            idToCounter.put(id, newCount);
            // add to the new key - curCount + 1
            countToIds.compute(newCount,
                    (k, v) -> {
                        if (v == null) {
                            return Sets.newHashSet(id);
                        } else {
                            v.add(id);
                            return v;
                        }
                    }
            );
        }
    }

    public void inc(Integer id) {
        updateCache(id, 1);
    }

    public void down(Integer id) {
        updateCache(id, -1);
        Integer count = idToCounter.get(id);

    }

    public int getMin() {
        Map.Entry<Integer, Set<Integer>> entry = countToIds.firstEntry();
        if (null == entry || entry.getKey().compareTo(0) <= 0) return -1;
        return entry.getValue().stream().findFirst().orElse(-1);
    }

    public int getMax() {
        Map.Entry<Integer, Set<Integer>> entry = countToIds.lastEntry();
        if (null == entry || entry.getKey().compareTo(0) <= 0) return -1;
        return entry.getValue().stream().findAny().orElse(-1);
    }

    public static void main(String[] args) {
        CountUpDown idcounter = new CountUpDown();
//        System.out.printf( idcounter.getMax());

        idcounter.inc(1);
        idcounter.inc(2);
        idcounter.inc(2);
        idcounter.down(2);
        idcounter.down(2);
        idcounter.down(2);
        System.out.println(idcounter.getMax());
    }
}
