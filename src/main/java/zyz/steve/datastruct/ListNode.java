package zyz.steve.datastruct;


public class ListNode {

    public int data;

    public ListNode next;

    public ListNode(int d) {
        data = d;
        next = null;
    }
    @Override
    public String toString(){
        return String.format("%d->%s",data,next==null?"null":String.valueOf(next.data));
    }

}
