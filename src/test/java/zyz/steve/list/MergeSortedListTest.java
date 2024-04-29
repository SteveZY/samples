package zyz.steve.list;

import org.junit.Test;
import zyz.steve.datastruct.ListNode;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MergeSortedListTest {

    @Test
    public void mergeTwoLists() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(6);

        ListNode temp = MergeSortedList.mergeTwoLists(l1, l2);
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
    @Test
    public void mergeKLists() {
        ListNode l0=null;
        ListNode l1 = new ListNode(-1);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(11);

        ListNode l2 = null;
        ListNode l3 = new ListNode(6);
        l3.next = new ListNode(10);
//        l3.next.next = new ListNode(6);
         ListNode[] lists =
        {l0, l1, l2, l3};
//        new ArrayList<>(Collections.);
        ListNode temp = MergeSortedList.mergeKLists(lists);
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
    @Test
    public void mergeKListsPQ() {
        ListNode l0=null;
        ListNode l1 = new ListNode(-1);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(11);

        ListNode l2 = null;
        ListNode l3 = new ListNode(6);
        l3.next = new ListNode(10);
        //        l3.next.next = new ListNode(6);
        ListNode[] lists =
                {l0, l1, l2, l3};
        //        new ArrayList<>(Collections.);
        ListNode temp = MergeSortedList.mergeKListsPQ(lists);
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
}