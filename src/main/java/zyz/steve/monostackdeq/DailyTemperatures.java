package zyz.steve.monostackdeq;

import java.util.ArrayDeque;
import java.util.Arrays;

public class DailyTemperatures {
    // LC 739
    private static int[] findNextWarmerDay(int[] temperatures){
        int n = temperatures.length;
        int[]res = new int[n];

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = n-1 ; i >=0 ; i--) {
            while (!stack.isEmpty() && temperatures[i]>= temperatures[stack.peek()]) stack.pop();
            res[i] = stack.isEmpty()?0:stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = new int[]{73, 74, 75, 71,76};
        System.out.println(Arrays.toString(findNextWarmerDay(a)));
//        Arrays.equals()
    }
}
