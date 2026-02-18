package zyz.steve.monostackdeq;

import java.util.ArrayDeque;
import java.util.Deque;

// LC 42
public class RainWaterTrapper {

    public static void main(String[] args) {
        int[] height = new int[]{2, 1, 0, 1, 3,};
//        for
        System.out.println(trapWater(height));
        height = new int[]{2, 1, 0, 0, 0, 1, 3};
        System.out.println(trapWater(height));
        height = new int[]{2, 2, 9};
        System.out.println(largestRectInHisto(height));
        height = new int[]{3, 2, 1};
        System.out.println(largestRectInHisto(height));
        System.out.println(largestRectHistroBruteForce(height));
        height = new int[]{3};
        System.out.println(largestRectHistroBruteForce(height));
    }

    private static int trapWater(int[] height) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 维持 一个 递减 栈, 即top 是最小的
                // 一旦发现 一个 右侧的 高点 墙壁， 就可以计算 跟 之前较小的位置差，可以计算储水量
                int pre = stack.pop();
                if (stack.isEmpty()) break; // 左侧没有找到 高点，即 无左墙壁 就暂停
                int coverHeight = Math.min(height[i], height[stack.peek()]);
                res += (coverHeight - height[pre]) * (i - stack.peek() - 1);
            }
            stack.push(i);
        }
        return res;
    }

    private static int largestRectHistroBruteForce(int[] height) {
        // 固定高度, 向左右找 边界 ，即比当前 值 小的 就是边界，就可以确定宽了
        // 单调栈的思路 源自 这个方法， 向右遍历高度，如果高度 一直 增加，就 都进栈。
        // 一旦看到一个低于栈顶元素的 值 就说明 右边界找到了，然后 弹出 栈顶，而新栈顶 就是 旧栈顶高度 所能跨越范围的 左边界
        // 左右边界 都找到了， 宽度 也就有了
        int ans = 0;

        for (int i = 0; i < height.length; i++) {
            int curHeight = height[i];
            int left = i, right = i;
            while (left >= 0 && height[left] >= curHeight) left--;
            while (right < height.length && height[right] >= curHeight) right++;
            // found left and right , then width
            int width = right - left - 1;
            ans = Math.max(ans, width * curHeight);
        }
        return ans;
    }

    private static int largestRectInHisto(int[] height) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                Integer preIdx = stack.pop();
                int preHeight = height[preIdx];
                int width = 0;
                if (!stack.isEmpty()) width = i - stack.peek() - 1;
                else width = i;
                res = Math.max(res, width * preHeight);

            }

            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer preIdx = stack.pop();
            int preHeight = height[preIdx];
            int width = 0;
            if (!stack.isEmpty()) width = height.length - stack.peek() - 1;
            else {
                width = height.length;
            }
            res = Math.max(res, width * preHeight);

        }
        return res;
    }
}
