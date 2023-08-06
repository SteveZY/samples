package zyz.steve.list;

import zyz.steve.datastruct.ListNode;

public class RecursiveReverse {

    static ListNode head; // head of list
    static ListNode headOfNonReversedPart;
    static int counter;

    static ListNode reverseN(ListNode head, int n){
        if(n<=1){
            headOfNonReversedPart = head.next;
            return head;
        }
        ListNode last = reverseN(head.next,n -1);
        head.next.next = head;//反转，以指向当前节点
        head.next = headOfNonReversedPart;

        return last;
    }
    static ListNode reverseEndN(ListNode head, int n){
        //Reverse the end and put them to the beginning of the list
        //13 20 9 8 6
        //6 8 13 20 9
        if (head == null || head.next == null) {
            //return the last one
            counter++;
            return head;
        }
        ListNode last = reverseEndN(head.next, n);
        counter ++;
        if(counter - n  == 1) head.next = last;
        if(counter > n) return last;
        head.next.next = head;
        head.next=null;
        return last;
    }
    static ListNode reverseNFromEnd(ListNode head, int n){
        //Reverse the end and put them to the beginning of the list
        //13 20 9 8 6
        //6 8 13 20 9
        if (head == null || head.next == null) {
            //return the last one
            counter++;
            return head;
        }
        ListNode last = reverseNFromEnd(head.next, n);
        counter ++;
        if(counter - n  == 1) head.next = null;
        if(counter > n) return last;
        head.next.next = head;
        head.next=RecursiveReverse.head;
        return last;
    }
    static ListNode reverseTail(ListNode cur, ListNode pre){
        //https://www.pixelstech.net/article/1474689232-Traditional-recursion-vs-Tail-recursion

        if(cur.next == null) {
            cur.next = pre;
            return cur;
        }

        ListNode next = cur.next;
        cur.next = pre;

        return reverseTail(next, cur);

//        return null;

    }
    static ListNode reverse(ListNode head) {
        //https://www.geeksforgeeks.org/reverse-a-linked-list/
        if (head == null || head.next == null) {
            //return the last one
            return head;
        }

        /* reverse the rest list and put
        the first element at the end */
        //递归下一个节点
        ListNode rest = reverse(head.next);
        //返回后，调整指针，令上一行被递归的节点的next指向当前节点
        head.next.next = head;

        /* tricky step -- see the diagram */
        head.next = null;

        /* fix the head pointer */
        return rest;
    }

    /* Function to print linked list */
    static void print() {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    static void push(int data) {
        ListNode temp = new ListNode(data);
        temp.next = head;
        head = temp;
    }
}
