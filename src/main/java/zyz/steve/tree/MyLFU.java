package zyz.steve.tree;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

///https://leetcode.cn/problems/lfu-cache/solutions/186348/lfuhuan-cun-by-leetcode-solution/
//这个是直接利用了现成 的 LinkedHashSet
//需要维护一个 min
public class MyLFU {

    class Node {
        int key;
        int value;
        int freq = 1;
        public Node() {
        }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> cache;

    Map<Integer, LinkedHashSet<Node>> fMap;

    int capacity;
    int size;
    int min;
    int max=0;

    public MyLFU(int capacity) {
        cache = new HashMap<>(capacity);
        fMap = new HashMap<>();
        this.capacity = capacity;

    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node ==null)return -1;
        freqInc(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            freqInc(node);

        } else {
            if(size == capacity){
                //已到最大容量，先移除
                Node tobeDel = removeNode();
                cache.remove(tobeDel.key);
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key,newNode);
            addNode(newNode);
            size++;

        }
    }

    private void addNode(Node newNode) {
        fMap.putIfAbsent(newNode.freq,new LinkedHashSet<>());
        fMap.get(newNode.freq).add(newNode);
        min=newNode.freq;
//        max = Math.max(newNode.freq, max);
    }

    private Node removeNode() {
        LinkedHashSet<Node> minSet = fMap.get(min);
        Node tobeRemoved = minSet.iterator().next();
        minSet.remove(tobeRemoved);

        return tobeRemoved;
    }

    private void freqInc(Node node) {
        int fr = node.freq;
        LinkedHashSet<Node> set = fMap.get(fr);
        set.remove(node);
        if(fr == min && set.size()==0){
            min = fr+1; //最少被访问的 也增加，需要调整 min
        }
        //将节点加入新的 freq 记录
        node.freq++;
        max = Math.max(node.freq,max);
        fMap.putIfAbsent(fr+1,new LinkedHashSet<>());
        fMap.get(fr+1).add(node);
    }

}
