package zyz.steve.monostackdeq;

import zyz.steve.datastruct.ListNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NextGreater {
    // 找到 一个数组 中比当前 元素大的 第一个 元素, 若没有 则 输出 -1
    // 例如，输入 nums = { 2, 1,2,4,3} , 输出 = {4,2,4-1,-1 }
    // 第一个元素 2， 其 后面 比 它 大的元素 就是 4 （ idx =3）
    // 从后往前 遍历， 只要 发现 比栈顶 （就是目前大元素的最小值）更大的元素 就保存下来，以便于 跟下一个 比较
    // 使用 单调栈，  确保 栈中 元素 总是 大于 当前元素，
    private static int[] nextGreaterEle(int[] nums) {
        int[] res = new int[nums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 要计算 第一个 大于当前 的 ，也就是 要找的值必然位于 当前值的后面， 故从 数组后面 开始 遍历 会比较方便
        for (int i = nums.length - 1; i >= 0; i--) {
            int curEle = nums[i];
            while (!stack.isEmpty() && curEle >= stack.peek()) {
                // 保持 单增 栈， 发现比当前 <= 就给扔掉，这样 就都是保存着大的，下一个元素进来，如果更大，那之前扔掉元素 只会更小
                // 保证 栈中元素 从小 到 大 （top -> bottom)
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(curEle);
        }
        return res;
    }

    private static int[] nextGreaterEleI(int[] nums1, int[] nums2) {
        int[] res ; //= new int[nums1.length];
//        ArrayDeque<Integer> stack = new ArrayDeque<>();
        res = nextGreaterEle(nums2);
        Map<Integer, Integer> numIdx = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            numIdx.put(nums2[i], res[i]);
        }
        int[] res1 = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res1[i] = numIdx.get(nums1[i]);
        }
        return res1;
    }

    // LC 503
    // circular array
    private static int[] nextGreaterII(int [] nums){
        int[] res = new int[nums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;
        for (int i = 2*nums.length -1; i >=0 ; i--) {
            int curEle = nums[i %  n];
            while (!stack.isEmpty() && curEle >= stack.peek()) {
                stack.pop();
            }
            res[i%n] = stack.isEmpty()? -1: stack.peek();

            stack.push(curEle);
        }
        return res;
    }
    private static ListNode reverseLinkedList(ListNode node){
        if (node.next == null) return node; // tail
        ListNode ret = reverseLinkedList(node.next);
//        node.next = ret;
//        ret.next = node;
        node.next.next=node; // reverse
        return ret; // return tail
    }

    // 上述reverse List DFS 的特列， 也可以用stack 实现，
    private static  ListNode reserveListStack(ListNode head){
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        ListNode node = head;
        while (node!=null){
            stack.push(node);
            node = node.next;
        }
        ListNode newHead = stack.pop();
        node = newHead;
        while (!stack.isEmpty()){
            node.next = stack.pop();
            node = node.next;
        }
        node.next = null;
        return newHead;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterEle(new int[]{2, 1, 2, 4, 3})));
        System.out.println(Arrays.toString(nextGreaterEle(new int[]{2, 5, 2, 4, 3})));
        System.out.println(Arrays.toString(nextGreaterII(new int[]{1,2,1})));

        System.out.println(Arrays.toString(nextGreaterEleI(new int[]{4,1,2}, new int[]{1, 3, 4,2})));

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;

        ListNode newheade = reserveListStack(node1);
//        node1.next = null;
        ListNode node = newheade;
        while (node != null){

            System.out.println(node);
            node = node.next;
        }


    }
}
