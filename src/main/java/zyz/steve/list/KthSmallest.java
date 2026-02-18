package zyz.steve.list;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
public class KthSmallest {
    public static int findKthSmallest(int[][] m, int k) {
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < m.length; i++) {
            // 将 每行的 第一个元素 放入 Q ， 并 记录 元素的row， col 坐标
            // 元素 的格式 为， {元素值， row，col}
            q.offer(new int[]{m[i][0], i, 0});// row i, col 0
        }
        int counter = 1; // 计数 器
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (counter == k) return cur[0]; // 找到k 返回
            if (cur[2] < m.length - 1) // 当当前元素 不是 其所在行的 最后一个 的时候，将它 下一个 元素 放去 q 中
                q.offer(new int[]{m[cur[1]][cur[2] + 1], cur[1], cur[2] + 1});
            counter++;
        }
        return 0;
    }

    public static int findKthSmallestBinarySearch(int[][] m, int k) {
        int n = m.length;
        int low = m[0][0]; //  matrx 中最小值
        int high = m[n - 1][n - 1]; // matrix 中最大值
        while (low < high) {
            int mid = (high - low) / 2 + low; // 中值
            // 找到 中 值， 通过 count  比 中值 小的
            // if 比mid 小的数，的个数小于 k， 则 k 在 较大的一侧，此时令 low = mid+1；

            if (checkWhichSideKIs(m, k, n, mid)) high = mid;
            else low = mid + 1;
            //otherwise ， k在较小一侧， high=mid
        }
        return low;
    }

    // 检测 K 在 中 值 的哪一侧
    private static boolean checkWhichSideKIs(int[][] m, int k,int n, int mid) {
        //总是从 左下角开始， {n-1,0}， 也可以从 右上角开始 {0,n-1}
        int row = n - 1;
        int col = 0;
        int counter =0;
        //要充分利用 列也是 从小到大升序排列的，

        while (row>=0 && col<n){
            if(m[row][col]<=mid){
                counter+=row+1;
                col++;
            }else {
                row--;//next row
            }
        }
        return k<=counter;
    }

}
