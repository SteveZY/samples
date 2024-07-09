package zyz.steve.tree;

import java.util.*;
//https://leetcode.cn/problems/all-oone-data-structure/

public class AllOne {

    Map<String,Integer> map;
    TreeMap<Integer,Set<String>> freq;
    public AllOne() {
        map=new HashMap<>();
        freq=new TreeMap<>();
    }

    public void inc(String key) {

        //下面这段应该也工作，
//        int cnt;
//        cnt = map.getOrDefault(key, 0);
//        if(cnt > 0 ){ //如果key在
//            freq.get(cnt).remove(key);//从当前 freq set 移除
//            if(freq.get(cnt).size()==0) freq.remove(cnt); //若set为空了，则从freqMap移除
//        }
//        map.put(key, cnt + 1);//放入 cache中
//        freq.putIfAbsent(cnt + 1, new HashSet<>()); //加入freq Map
//        freq.get(cnt + 1).add(key);

        map.put(key, map.getOrDefault(key, 0) + 1);
        int count = map.get(key);
        if (count > 1) {
            //说明之前存在，检查是否要移除之前的 entry 还是只移除这个 新key
            if (freq.get(count - 1).size() == 1) {
                freq.remove(count - 1);
            } else {
                freq.get(count - 1).remove(key);
            }
        }
        freq.putIfAbsent(count, new HashSet<>());
        freq.get(count).add(key);
    }

    public void dec(String key) {
        int count = map.get(key);//目前的频率
        if (freq.get(count).size() == 1) {
            freq.remove(count);
        } else {
            freq.get(count).remove(key);
        }
        if (count == 1) {
            map.remove(key);
        } else {
            map.put(key, count - 1);
        }
        if (count > 1) {
            freq.putIfAbsent(count - 1, new HashSet<>());
            freq.get(count - 1).add(key);
        }
    }

    public String getMaxKey() {
        if(map.size()>0){
            Integer a=freq.lastKey();
            return freq.get(a).iterator().next();
//            for(String s:freq.get(a)){return s;}
        }
        return "";
    }

    public String getMinKey() {
        if(map.size()>0){
            Integer a=freq.firstKey();
            for(String s:freq.get(a)){return s;}
        }
        return "";
    }

}
