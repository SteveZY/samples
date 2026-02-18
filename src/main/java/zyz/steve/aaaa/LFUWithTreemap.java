package zyz.steve.aaaa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class LFUWithTreemap {
    TreeMap<Integer, Set<String>> treeMap ;
    Map<String, Integer> hmap ;
    public LFUWithTreemap(int capacity) {
        treeMap = new TreeMap<>();
        hmap = new HashMap<>();
    }
    public void incr(String key){
        // get key from the hmap
        Integer freq = hmap.getOrDefault(key,  0);
        // update the old entry
        updateTreeMap(key, freq);
        //
        freq++;
        hmap.put(key, freq);

        // add key to the tree map
        treeMap.computeIfAbsent(freq, (k) -> new HashSet<>())
                .add(key);


    }
    public void decr(String key){
        // get key from the hmap
        Integer freq = hmap.getOrDefault(key,  0);

        updateTreeMap(key, freq);
        // when empty, nothing needs to be done
// update freq
        freq--;
        hmap.put(key, freq);// replace with the new freq
        //
        treeMap.computeIfAbsent(freq, (k) -> new HashSet<>()).add(key);
    }

    private void updateTreeMap(String key, Integer freq) {
        // update treemap
        Set<String> keys = treeMap.get(freq);
        if(keys != null ) { // init keys
            keys.remove(key);
        }
        // after the removal, the entry might be empty
        if(keys != null && keys.isEmpty()){
            treeMap.remove(freq);
        }
    }

    public String getMin(){
        return treeMap.firstEntry().getValue().stream().findFirst().orElse(null);
    }
    public String getMax(){
        return treeMap.lastEntry().getValue().stream().findFirst().orElse(null);
    }
    public static void main(String[] args) {
        LFUWithTreemap lfu = new LFUWithTreemap(10);
        lfu.incr("a");
        lfu.incr("a");
//        lfu.incr("a");
//        lfu.incr("b");
        lfu.incr("b");
        lfu.incr("b");
        lfu.incr("b");


        System.out.println(lfu.getMax());
        System.out.println(lfu.getMin());

        lfu.decr("b");
        lfu.decr("b");

        System.out.println(lfu.getMax());
        System.out.println(lfu.getMin());


    }

}
