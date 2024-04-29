package zyz.steve.general;

import java.util.HashMap;
import java.util.Map;

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
    }
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            //替换
            node.data = value;
            remove(node);
            toTail(node);
            //            return;
        } else {
            if (map.size() >= capacity) {
                removeTail();
            }
            node = new Node(value, key);
            toTail(node);
            map.put(key, node);
        }
    }

    private void removeTail() {
        map.remove(tail.key);
        tail.pre.next = null;
        tail = tail.pre;
    }

    public Integer get(int key) {
        if(map.size() == 0) return null;
        Node node = map.get(key);
        if(node == null) return null;
        remove(node);
        toHead(node);

        return node.data;
    }

    private void toHead(Node node) {
        if (head != null) {
            node.next = head.next
            ;
        }
        head = node;
    }

    private void toTail(Node node) {
        node.pre = tail;
        if (tail != null) {
            tail.next = node;
        }
        tail = node;

    }

    private void remove(Node node) {
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
    }

}
