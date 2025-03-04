package zyz.steve.dp;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//#300: https://leetcode.com/problems/longest-increasing-subsequence/
// Binary Indexed Tree
//https://zhuanlan.zhihu.com/p/99167607#:~:text=%E6%A0%91%E7%8A%B6%E6%95%B0%E7%BB%84%E6%88%96%E4%BA%8C,%E7%9A%84%E5%89%8D%E7%BC%80%E5%92%8C%EF%BC%8C%20%E5%8C%BA%E9%97%B4%E5%92%8C%E3%80%82
//https://www.baeldung.com/cs/fenwick-tree
public class LengthOfLIS {
    public static int getLenOfLongestIncSubseq(int [] m){
        int[] len = new int[m.length];//存储所有位置，从其开始目前构造单调递增序列 最长长度，即为那个DP 数据
        Arrays.fill(len,1);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < i; j++) {
                //查看 i 之前的所有元素，只要比i 小，再查看 其位置的 值 多上 i 这个数字后，是否比 当前 到i 的 LIS 更大
                if(m[j]<m[i] && len[i]<len[j]+1){
                    len[i] = len[j] + 1;
                }
            }
        }
        int max = 1;
        for (int j : len) {
            max = Math.max(max, j);
        }
        return max;
    }
    static int binarySearchToGetInsertIdx(List<Integer> list, int target ){
//        int low;
//        int high;
        int low =0, high = list.size();//high 可高于最后一个indx，即为 要加入list的最后，扩展该list
        while (low < high){
            int med = low + (high - low) / 2;
            if (list.get(med) < target){
                //target 较大，插入位置只能位于后半部分
                low = med + 1;
            }else {
                //在前半部分查找插入位置
                high = med;
            }
        }

        return low;
    }
    //n*logn 方法
    public static int getLenOfLongestIncSubseqFaster(int [] m){
        //遍历数组，将元素放入有序的结果序列，基本步骤
        //若当前元素大于所有LIS 的元素，加到末尾
        //否则 用其替换 其应该放入位置的元素
        // 例子： 给定 5，4，3
        // i=0 LIS is empty, 将5 添加到末尾
        // i=1 LIS=[5], 4 的位置就应该是 第0 位，更新LIS 为 [4]
        // i=2 LIS=[4], 3 应在 第 0 位， 更新 LIS 位 [3]
        // 最后 LIS 即为 [3], 要求的结果为 LIS.size(), 为 1
        // 这样的 做法 LIS最后存储的 大概率并不是 那个 实际的 LIS， 但是长度必然是一样的
        //例子2: 5，7，1
        // i=0  LIS = 【5】
        //i =1 LIS = 【5，7】
        //i =2 ，此时由于1 应该放入第0位 替换5， 故 LIS =【1，7】，而真实 LIS 应位 【5，7】，但是长度是一样的
        //每次 发生有元素 要 替换之前的较小元素时， 相当于 一个新 LIS 开始了，至于这个新的是否更长，就要看后续子序列的单增情况，
        // 如果更长，则整个LIS 的元素 就会被这个新找到 全部 替换掉，最后必然能找到 最长的 LIS

        //存储找到的单增序列
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m.length; i++) {
            int idx = binarySearchToGetInsertIdx(list, m[i]);
            if(idx<list.size()){
                list.set(idx,m[i]);
            }else
            {
                list.add(m[i]);
            }
        }
        return list.size();

    }

    public static void main(String[] args) {
        int [] m = {9, 1,2,3,4,5,6,7};
        System.out.println(getLenOfLongestIncSubseqFaster(m));
    }
}
