package zyz.steve.list;

//https://leetcode.com/problems/merge-k-sorted-lists/description/

import zyz.steve.datastruct.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 */
public class MergeSortedList {
    public static ListNode mergeTwoRecursively(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        if(l1.data< l2.data){ // l1 的当前节点较小，就将其next 设为 后续 节点merge 后结果
            l1.next = mergeTwoRecursively(l1.next,l2);
            return l1;
        }else {
            l2.next =  mergeTwoRecursively(l2.next, l1);
            return l2;
        }
    }
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
        int mid =( l + r) / 2; // 分区
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
            tail.next = xxx;// 令要返回到的list 尾， 指向 刚刚弹出的最小元素
            tail = tail.next;
            if (xxx.next != null) {
                pq.offer(xxx.next);
            }
        }
        return head.next;
    }

    public static int[] mergeSortedArray(int[]a, int[]b){
        int[] newArray = new int[a.length + b.length];
        mergeArray(a, b, 0,0, newArray,0);
        return newArray;
    }
    public static void mergeArray(int[]a, int[]b, int aIdx, int bIdx, int[] target, int tIdx){
        if(aIdx>=a.length) {
            // a 的 元素 使用完毕
            for (int i = bIdx,j=0; i < b.length; i++,j++) {
                target[tIdx+j] = b[i];
            }
            return;
        }
        if(bIdx >= b.length){
            // b 的元素 使用完毕
            for (int i = aIdx,j=0; i < a.length; i++, j++) {
                target[tIdx+j] = a[i];
            }
            return;
        }
        if(a[aIdx] < b[bIdx]){
            target[tIdx] = a[aIdx];
            mergeArray(a,b,aIdx+1,bIdx,target,tIdx+1);
        }else {
            target[tIdx] = b[bIdx];
            mergeArray(a,b,aIdx,bIdx+1, target,tIdx+1);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 5, 8};
        int[] b = new int[]{0, 4, 18,20,21,45};
        int[] merged = mergeSortedArray(a, b);

        System.out.println(Arrays.toString(merged));
    }

}
