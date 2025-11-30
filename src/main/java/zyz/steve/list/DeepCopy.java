package zyz.steve.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Leetcode 138
class Node {
    int val;
    Node next, random;

    Node(int val) {
        this.val = val;
    }

//    public int val {
//        return val;
//    }

    public Node random() {
        return random;
    }

    public Node next() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setRandom(Node random) {
        this.random = random;
    }
}

/**
 *
 */
public class DeepCopy {
    static Node deepCopy(Node head) {
        // 新节点 插入 到被copy 节点的后面
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode; //插入新节点
            cur = cur.next.next; // cur 指向 下一个老节点
        }
        cur = head;
        // 新的节点 random  指向 对应的 新 节点, 也就 是 新节点 的 random 指向 被拷贝 original 节点的 random 指向 节点的 下一个 节点
        // cur.next(新节点).random = cur(老节点).random.next
        while (cur != null) {
            // cur.next 为拷贝后的新节点，
            cur.next.random = cur.random==null? null: cur.random.next;
            cur = cur.next.next;
        }
        Node newHead = head.next;
//        cur = newHead;
        Node orig = head;
        while (orig != null) {
            Node copied = orig.next; // get the copy
//            cur.next = copied.next;
            orig.next = copied.next; // 恢复 原始 列表的 next 指针
            copied.next = copied.next ==null ? null: copied.next.next;
            orig = orig.next;
        }

        return newHead;
    }
    /**
     * 每个节点数值不一样还行，一旦一样就不行了
     * @param head
     * @return
     */
    static Node deepCopyWithMap(Node head) {
        Map<Integer, Node> map = new HashMap<>();
        Node cur = head;
        // create shallow copy
        while (cur != null) {
            Node nn = new Node(cur.val);
            map.put(cur.val, nn);
            cur = cur.next;
        }
        // copy the pointers
        cur = head;
        while (cur != null) {
            Node newCur = map.get(cur.val);
            if (cur.next != null)
                newCur.next = map.get(cur.next.val);
            if (cur.random != null)
                newCur.random = map.get(cur.random.val);
            cur = cur.next;
        }
        return map.get(head.val);
    }

    //    static Node toList(int [][] array) {
//        List<Node> nodes = new ArrayList<>();
//        for (int i = 0; i < array.length; i++) {
//            Node nn = new Node(array[i][0],null,null);
//            nodes.add(nn);
//        }
//        for (int i = 0; i < array.length; i++) {
//            nodes.get()
//        }
//        return nodes.get(0);
//    }
    public static void main(String[] args) {
        Node head = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        head.setNext(n1);
        head.setRandom(n1);
        n1.setRandom(n1);
        n1.setNext(n2);
//        n2.setRandom(head);
        Node toPrint = head;
        while (toPrint != null) {
            System.out.println(toPrint);
            toPrint = toPrint.next;
        }
        Node copied = deepCopyWithMap(head);

        while (copied != null) {
            System.out.println(copied + " --- " +  copied.val);
            copied = copied.next;
        }
        copied = deepCopy(head);
        while (copied != null) {
            System.out.println(copied + " --- " +  copied.val+"--> "+ (copied.random == null ? "null" : copied.random.val));
            copied = copied.next;
        }
    }
}
