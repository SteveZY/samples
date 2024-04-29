package zyz.steve.general;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestUnsortedSub {
    //使用栈
    public static int findShortestUnsortedSub(int []array){
        //从左侧开始找到需要向左移动最多的元素，及其应该在 位置 leftMostIdx
        Deque<Integer> stack = new ArrayDeque<>();
        //下为 本算法的 invariant
        //保存当前未排序子数组最左侧index，初始化成最大
        //意为：开始循环时，假定数组是已经拍好序的
        int leftIdx = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            //reversed, find the right position for this (i )th element
            while (!stack.isEmpty() && array[stack.peek()] > array[i]) {
                //一旦发现有元素顺序 反了，就马上更新leftIdx，并最终确定其正确位置，
                //leftIdx 更新过后 就再也不可能会比 i 小了
                leftIdx = Math.min(leftIdx, stack.pop());
            }
            if (i < leftIdx) {
                //顺序正确的，压栈，继续循环

                stack.push(i);
            }
        }

        stack.clear();
        //同上
        //从最右侧开始找到 应该向右移动最多的元素，及其应该在的位置的 rightMostIdx
        int right = 0;//array.length;
        for (int j = array.length - 1; j >= 0; j--) {
            while (!stack.isEmpty() && array[stack.peek()] < array[j]) {
                right = Math.max(right, stack.pop());
            }
            if (right < j) {
                stack.push(j);
            }
        }

        int lenSubArray = right - leftIdx + 1;
        return lenSubArray > 0 ? lenSubArray : 0;
    }

    //不用栈
    //假设题中最短子数组存在且范围为 [left, right]，则在原数组中，
    // [0, left-1] 区间内的所有元素大小均不超过目标区间内的元素，
    // [right+1, n -1] 区间内的所有元素则均不小于目标区间。此即为 本算法的invariant

    //那么从右向左遍历时，一旦发现大于右侧的最小值，则说明反序发生了，需要更新左边界 以确保 invariant可以维持
    //反之，从左向右时，有小于左侧最大值，需要更新右边界，
    public static int findSWoStack(int [] nums){
        int len = nums.length;
        int left = len-1; //初始化左边界 为最大
        int min = nums[left];
        //先找左侧边界
        for(int i = len -2; i>=0; i--){
            if(nums[i] <= min){
                //左侧第i个元素，发现比右侧最小值大的，invariant被破坏。
                //更新最小值，并调整left，以维持invariant
//                left = i;
                min = nums[i];
            }else
            {
                left = i;
            }
        }
        if(left == len-1) return 0;//已经排好序了
        //右侧边界
        int max = nums[0];
        int right = 0;
        for (int i = 1;i< len;i++){
            if(nums[i]>= max){
                max = nums[i];
            }else {
                //找到一个
                right = i;

            }
        }
        int lenSubArr = right - left + 1;
        return Math.max(lenSubArr, 0);

    }
    public static Integer isSorted(int [] a){
        int min = a[0];//升序排列时
        int max = a[0];//降序排列时
        Integer res = null;
        //仅遍历一次判断
        for(int i = 1; i< a.length; i++){
            if(a[i] >= max){
                if (res != null && !res.equals(1)) {
                    return 0;
                } else {
                    res = 1;
                }
                max = a[i];//升序排列时，当前值应不小于左侧最大值
            }else {
                //否则不是升序

                if(a[i] <= min){
                    if (res != null && !res.equals(-1)) {
                        //如果已经设置为升序排列，则说明时无序的
                        return 0;
                    } else {
                        res = -1;
                    }
                    min = a[i];
                }else {
                    return 0;//不是降序也不是升序
                }
            }


        }
        return res;
    }

}
