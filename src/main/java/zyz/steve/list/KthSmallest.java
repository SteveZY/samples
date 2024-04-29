package zyz.steve.list;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
public class KthSmallest {
    public static int findKthSmallest(int[][] m, int k){
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o->o[0]));
        for (int i = 0; i < m.length; i++) {
            q.offer(new int[]{m[i][0],i,0});// row i, col 0
        }
        int counter=1;
        while (!q.isEmpty()){
            int[] cur = q.poll();
            if(counter==k) return cur[0];
            if(cur[2]<m.length-1)
                q.offer(new int[] {m[cur[1]][cur[2] + 1],cur[1],cur[2]+1});
            counter++;
        }
        return 0;
    }

    public static int findKthSmallestBinarySearch(int[][] m, int k){
        int n = m.length;
        int low = m[0][0];
        int high = m[n-1][n-1];
        while (low<high){
            int mid = (high-low) / 2 + low;
            // if 比mid 小的数，的个数小于 k， 则 k 在 较大的一侧，此时令 low = mid+1；
            if(checkWhichSideKIs(m,k,n,mid))high=mid;
            else low=mid+1;
            //otherwise ， k在较小一侧， high=mid
        }
        return low;
    }

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
