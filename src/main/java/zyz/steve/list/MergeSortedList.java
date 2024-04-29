package zyz.steve.list;

//https://leetcode.com/problems/merge-k-sorted-lists/description/

import zyz.steve.datastruct.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 */
public class MergeSortedList {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null)return l2;
        if(l2==null)return l1;
        ListNode head = new ListNode(-1);
        ListNode tmp = head;
        while (l1!=null && l2!=null){
            if(l1.data<l2.data){
                tmp.next=l1;
                l1 = l1.next;
            }
            else {
                tmp.next = l2;
                l2= l2.next;
            }
            tmp = tmp.next;
        }
        tmp.next = l1==null? l2:l1;

        return head.next;

    }

    //divide and conquer
    public static ListNode mergeKLists(ListNode[] lists){
        return merge(lists, 0,lists.length-1);
    }
    public static ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid =( l + r) / 2;
        return mergeTwoLists(
                merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 这个方法和前两种方法的思路有所不同，我们需要维护当前每个链表没有被合并的元素的最前面一个，
     * k 个链表就最多有 k 个满足这样条件的元素，
     * 每次在这些元素里面选取 val 属性最小的元素合并到答案中。
     * 在选取最小元素的时候，我们可以用优先队列来优化这个过程
     * @param lists
     * @return
     */
    public static ListNode mergeKListsPQ(ListNode[] lists){
        ListNode head = new ListNode(0);
        ListNode tail = head;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.data));
        for (int i = 0; i < lists.length; i++) {
            if (null != lists[i]) {
                pq.offer(lists[i]);
            }

        }
        while (!pq.isEmpty()) {
            ListNode xxx = pq.poll();
            tail.next = xxx;
            tail = tail.next;
            if (xxx.next != null) {
                pq.offer(xxx.next);
            }
        }
        return head.next;
    }

}
