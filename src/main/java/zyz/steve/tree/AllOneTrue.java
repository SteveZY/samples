package zyz.steve.tree;

import java.util.*;

//之前那个不是 O(1) , 这个用双向链表，是真正的 O(1)

//这个是LC官方题解
public class AllOneTrue {
    class Node {

        Node prev;
        Node next;
        Set<String> keys;
        int count;

        public Node() {
            this("", 0);
        }

        public Node(String key, int count) {
            this.count = count;
            keys = new HashSet<>();
            keys.add(key);
        }

        /**
         * 插入到this 的 直接 后面
         * before insertion:
         * this  <----- .prev
         * .next  -----> orig_next
         * <p>
         * after insertion:
         * this  <---- .prev ; .next-----> orig_next
         * .next ----->    node     <------.prev
         *
         * @param node
         * @return
         */
        public Node insert(Node node) {  // 在 this 和其 next 中间 插入 given node
            node.prev = this; //先更新 given node 的 前后指针
            node.next = this.next;//given node next 指向 this 的 next
//2. 修改 老节点的指针
            node.prev.next = node;//将this.next 指针 置为 given node，这之后 this.next 就被修改了
            node.next.prev = node;//原this.next 指向的节点 prev 指向 当前 given node
            return node;
        }

        public void remove() {
            //同插入相反，将this 的 前 后节点连接起来。
            this.prev.next = this.next;//将 this 的 next， 和 prev 分别给到 其 前，后 节点
            this.next.prev = this.prev;
        }
    }

    //With doubly linked list
    Node root;
    Map<String, Node> nodes;

    public AllOneTrue() {
        root = new Node();
        root.prev = root;
        root.next = root;
        nodes = new HashMap<>();
    }

    /**
     * 这个 不同于 LFU cache ，它没有 一个容量限制， 增加的时候，不用考虑 移除 老的项目
     * 但应该 不维护 min 计数  但配合 linkedHashSet 可以 做到 移除 不常用项目的
     *
     * @param key
     */
    public void inc(String key) {
        //简化版
        Node cur = nodes.getOrDefault(key, root);
        if (cur.next == root || cur.next.count > cur.count + 1) {
            //插入一个 count+1 计数的节点 到 当前节点的 后面，并更新cache
            nodes.put(key, cur.insert(new Node(key, cur.count + 1)));
        } else {
            //当下一个节点的 访问频次 ，就是 count+1 是，直接加入
            cur.next.keys.add(key);
            nodes.put(key, cur.next);
        }
        if (cur != root) {//不是根节点的话，说明在链表中，需要从旧的 计数 项目中 移除
            cur.keys.remove(key);
            if (cur.keys.isEmpty()) cur.remove();
        }

//        if(nodes.containsKey(key)){
//            //in cache
//            Node curKeyNode = nodes.get(key);
//            if(curKeyNode.next == root|| curKeyNode.next.count>curKeyNode.count+1){
//                //插入一个 count+1 计数的节点当前节点的 后面，并更新cache
//                nodes.put(key,curKeyNode.insert(new Node(key,curKeyNode.count+1)));
//            }else {
//                //下一个节点就是 count+1 的
//                curKeyNode.next.keys.add(key);
//                nodes.put(key,curKeyNode.next);
//            }
//            //将key 从 curKeyNode 移除；每个节点都是 多个 key的集合在里面
//            curKeyNode.keys.remove(key);
//            if(curKeyNode.keys.isEmpty()) curKeyNode.remove();
//        }else{
//            //not there
//            if(root.next ==root || root.next.count> 1){
//                //新节点，计数为1，插入到root的后面
//                nodes.put(key,root.insert(new Node(key,1)));
//            }else {
//                //也就说root 后面的节点 频次 为 1，故直接加入其中
//                root.next.keys.add(key);
//                nodes.put(key,root.next);
//            }
//        }
    }

    //题目中说明 key 肯定在，故减少检查 key存在性的问题
    public void dec(String key) {
        Node cur = nodes.get(key);

        if (cur.count == 1) {
            nodes.remove(key);
        } else {
            Node pre = cur.prev;
            if (pre == root || cur.count - 1 > pre.count) {
                //插入新节点
                nodes.put(key, pre.insert(new Node(key, cur.count - 1)));
            } else {
                //直接放到 prev 里面
                pre.keys.add(key);
                nodes.put(key, pre);
            }
        }
        cur.keys.remove(key);
        if (cur.keys.isEmpty()) cur.remove();

    }

    public String getMaxKey() {
        return root.prev != null ? root.prev.keys.iterator().next() : "";
    }

    public String getMinKey() {
        return root.next != null ? root.next.keys.iterator().next() : "";
    }

}
