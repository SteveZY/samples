package zyz.steve.aaaa;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
// 这个 PQ 需要 移除 已存在节点，速度太慢 O(n)
public class LFUWithPQ {
    class Node {
        String key;
        int value;
        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key);
        }
    }
    PriorityQueue<Node> pqMin;
    PriorityQueue<Node> pqMax;

    Map<String, Node> map = new HashMap<>();

    public LFUWithPQ(int capacity) {
        pqMin = new PriorityQueue<>(capacity, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });
        pqMax = new PriorityQueue<>(capacity, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                return o2.value - o1.value;
            }
        });
    }
// log N
    public void incr(String key) {
        // get the node
        Node node = map.computeIfAbsent(key, k -> new Node(key, 0));
        // inc by 1
        node.value++;
        // place in PQs
        pqMax.remove(node); // 移除的效率太低
        pqMin.remove(node);
        pqMax.offer(node);
        pqMin.offer(node);
    }
    // log N
    public void decr(String key) {
        // get the node
        Node node = map.computeIfAbsent(key, k -> new Node(key, 0));
        // inc by 1
        node.value--;
        pqMax.remove(node);
        pqMin.remove(node);
        // place in PQs
        pqMax.offer(node);
        pqMin.offer(node);
    }
    public String getMinKey() {
        return pqMin.peek().key;
    }
    public String getMaxKey() {
        return pqMax.peek().key;
    }
    public static void main(String[] args) {
        LFUWithPQ lfu = new LFUWithPQ(3);

        lfu.incr("a");
        lfu.incr("a");
        lfu.incr("a");
        lfu.incr("a");
        lfu.incr("a");
        lfu.incr("a");
        lfu.incr("a");
        lfu.incr("a");
        lfu.incr("b");
        lfu.incr("b");
        lfu.incr("b");
        lfu.incr("c");
        lfu.incr("c");


        System.out.println(lfu.getMaxKey());
        System.out.println(lfu.getMinKey());

        lfu.decr("b");
        lfu.decr("b");
        lfu.decr("d");

        System.out.println(lfu.getMaxKey());
        System.out.println(lfu.getMinKey());
    }
}
