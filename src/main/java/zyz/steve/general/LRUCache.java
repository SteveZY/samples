package zyz.steve.general;

import java.util.HashMap;
import java.util.Map;


//https://leetcode.com/problems/lru-cache/description/
//#146

//TODO: #460 https://leetcode.com/problems/lfu-cache/description/
public class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private Node head, tail;

    class  Node{
        Node pre;
        Node next;
        int data;
        int key;
        private Node(int value, int key){
            data = value;
            this.key = key;
        }
    }
    public LRUCache(int capacity){
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head  = new Node(-1,-1);
        tail  = new Node(-1,-1);
        head.next=tail;
        tail.pre = head;

    }
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            //替换
            node.data = value;
            remove(node);
            toTail(node);
        } else {
            if (map.size() >= capacity) {
                removeLast();
            }
            node = new Node(value, key);
            toTail(node);
            map.put(key, node);
        }
    }

    private void removeLast() {
        map.remove(head.next.key);
        remove(head.next);
    }

    public Integer get(int key) {
        if(map.size() == 0) return -1;
        Node node = map.get(key);
        if(node == null) return -1;
        remove(node);
        toTail(node);

        return node.data;
    }

    private void toHead(Node node) {
        //del head as well
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;

    }

    private void toTail(Node node) {
        node.next=tail;
        node.pre= tail.pre;
        tail.pre.next = node;
        tail.pre = node;

    }

    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre= node.pre;
    }
}
