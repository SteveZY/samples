package zyz.steve.tree;

import java.util.Arrays;

//https://www.youtube.com/watch?v=ZBHKZF5w4YU&t=853s
public class SegmentTree {
    int [] data;
    public SegmentTree(int [] nums){
        data = new int[nums.length*4];
        Arrays.fill(data, Integer.MAX_VALUE);
        build(nums,0,0,nums.length-1);
    }
    private void build(int[] nums, int pos, int low, int high){
        if(low == high){
            data[pos] = nums[low];
            return;
        }
        int mid = low+ ((high-low )>>1);
        //left first
        build(nums,2*pos+1,low,mid);
        //right child
        build(nums,2*pos+2,mid+1,high);
        //左右子节点较小值 更新 当前节点
        data[pos] = Math.min(data[2*pos+1],data[2*pos+2]);

    }
    //获取给定区间的最小值
    public int rangeQuery(int qlow, int qhigh, int low, int high, int pos){

        //完全覆盖
        if(qlow<= low && qhigh>= high)
            return data[pos];
        //no overlap, return max
        if(qlow>high || qhigh<low) return Integer.MAX_VALUE;

        //部分覆盖
        int mid = low+ ((high-low )>>1);
        //取两侧的最小值
        return Math.min(rangeQuery(qlow, qhigh,low,mid, 2*pos+1),rangeQuery(qlow,qhigh,mid+1,high,2*pos+2));
//        return 0;
    }

}
