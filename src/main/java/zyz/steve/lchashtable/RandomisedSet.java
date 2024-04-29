package zyz.steve.lchashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomisedSet {
    private Map<Integer,Integer> map;
    private List<Integer> index;
    public RandomisedSet() {
        map= new HashMap<>();
        index = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }else {
            map.put(val,index.size());
            index.add(val);
            return true;
        }
    }

    public boolean remove(int val) {
        if(map.containsKey(val)){
            Integer idx = map.remove(val);
            if(map.size()==0) {
                index.clear();
                return true;
            }
            //将最后一个元素放倒 要移除的元素 的 位置
            Integer last = index.get(index.size() - 1);

//            map.put(last,idx);

            index.set(idx,last);
            //移除最后一个元素
            index.remove(index.size()-1);
            return true;
        }else {
            return false;
        }
    }

    public int getRandom() {
        int idx = (int) (Math.random() * map.size());
        return index.get(idx);
    }

}
