package zyz.steve.monostackdeq;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

public class MotonicDeqTemplate {
    // 模版 , 递减队列
    // k - window size
    private static   int[] monotonicDeq(int[] nums, int k){
        //
        int N = nums.length;
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            // 确保队列 保持 单调，大小
            while (!q.isEmpty() && i-q.peekFirst() >=k) q.pollFirst();
            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()] ) q.pollLast(); // 单调性

            q.offerLast(i); // 然后才能加入新元素
            q.peekFirst();

        }

        return null; // 返回需要的 结果


    }
    private static Integer[] slidingWindowBrutal(int[] nums, int k){

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length -2; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < k+i; j++) {
                max = Math.max(nums[j],max);
            }
            res.add(max);
        }
        return (res.toArray(new Integer[nums.length-2]));
    }

    // PQ 解法
    private static Integer[] slidingWindowPQ(int[] nums, int k){
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length   ; i++) {
            q.offer(nums[i]);
            if(q.size()>=k) {
                int va = q.peek();
                res.add(va);
                q.remove(nums[i-k+1]);
            }
        }
        return (res.toArray(new Integer[nums.length-2]));
    }

    // 单调队列解法

    private static Integer[] slidingWinMotonicQ(int [] nums, int k){
        int N = nums.length;
        Deque<Integer> q = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            while (!q.isEmpty() && i - q.peekFirst() >= k){
                q.pollFirst(); // 若较大的元素 跟当前i 的距离 超过了k，则移除，保证跟较大的元素 在一个 k 大小的 window
            }
            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]){
                // 当前 i 元素较大， 将Q 中较小的 元素 都 丢掉，（q中都是存的当前 k window 中数值） 确保 q 是单调递减的
                q.pollLast();
            }
            q.offerLast(i); // 放到右侧，左侧都是较大的元素
//            q.;
            res.add(nums[q.peekFirst().intValue()]); // 收集 结果集
        }
        Integer[] ret = new Integer[N - k+ 1];
        for (int i = 0; i < ret.length; i++) {
         ret[i] = res.get(i+2);
        }
        return  ret;
//        return  res.toArray();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 1, 3, 6, 7};
        System.out.println(Arrays.toString(slidingWindowBrutal(nums, 3)));
        System.out.println(Arrays.toString(slidingWindowPQ(nums, 3)));
        System.out.println(Arrays.toString(slidingWinMotonicQ(nums, 3)));
    }

}
