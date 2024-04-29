package zyz.steve.array;

import java.util.ArrayDeque;
import java.util.zip.CheckedInputStream;

//https://leetcode.com/problems/largest-rectangle-in-histogram/description/
public class LargestRectangleArea {

    //暴力 遍历所有可能
    public static int largestRectangleArea(int[] h) {
        int a = 0;
        for (int i = 0; i < h.length; i++) {
            //            int curh = h[i];
            int left = i;
            int right = i;
            while (left - 1 >= 0 && h[left - 1] >= h[i]) {
                left--;
            }
            while (right + 1 < h.length && h[right + 1] >= h[i]) {
                right++;
            }
            int w = right - left + 1;
            a = Math.max(a, w * h[i]);
        }

        return a;
    }
    //单调栈
    /**
     * 如果有两根柱子 j0​
     *   和 j1​
     *  ，其中 j0  在 j1  的左侧，并且 j0的高度大于等于 j1 ，那么在后面的柱子 i向左找小于其高度的柱子时，j1会「挡住」j0，j0就不会作为答案了。
     */

    public static  int largestRectangleAreaWithStack(int[] h){

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < h.length; i++) {
            while (!stack.isEmpty() && h[stack.peek()]>h[i]){
                int height = h[stack.pop()];
                int width ;
                if(stack.isEmpty()){
                    width = i;
                }else {
                    width=i-stack.peek()-1;
                }
                area = Math.max(area,width*height);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int height = h[stack.pop()];

            while (!stack.isEmpty() && h[stack.peek()]==height) stack.pop();
            int width;
            if(stack.isEmpty())
                width=h.length;
            else width = h.length - stack.peek()-1;
            area = Math.max(area,width*height);

        }

        return area;
    }

    public static int largestRectangleAreaWithSentinel(int[] h) {

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int area = 0;

        int[] h2 = new int[h.length + 2];
        System.arraycopy(h, 0, h2, 1, h.length);
        h = h2;
        stack.push(0);
        for (int i = 1; i < h.length; i++) {
            while (h[stack.peek()] > h[i]) {
                int height = h[stack.pop()];
                int width;
                width = i - stack.peek() - 1;
                area = Math.max(area, width * height);
            }
            stack.push(i);
        }
        return area;
    }

    //两次循环找左右
    public static  int twoLoops(int []h){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        int [] left = new int[h.length];
        int [] right = new int [ h.length];
        for (int i = 0; i < h.length; i++) {
            while (!stack.isEmpty() && h[stack.peek()]>=h[i]){
                stack.pop();

            }
            left[i] = stack.isEmpty()?-1:stack.peek();
           stack.push(i);
        }
        stack.clear();
        for (int i = h.length-1; i >=0; i--) {
            while (!stack.isEmpty() && h[stack.peek()]>=h[i]){
                stack.pop();

            }
            right[i] = stack.isEmpty()?h.length:stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < left.length; i++) {
            area = Math.max(area, (right[i] - left[i] -1) * h[i]);
        }
        return area;
    }
}
