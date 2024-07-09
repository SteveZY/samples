package zyz.steve.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ProductExceptSelf {
    //https://leetcode.com/problems/product-of-array-except-self/description/?envType=daily-question&envId=2024-03-15
    public static int[] productExceptSelf(int[] nums){
        int[] ans = new int[nums.length];
        ans[0]=1;
        for (int i = 1; i < nums.length; i++) {
            ans[i]=nums[i-1]*ans[i-1];
        }
        //用一个遍历 r 存储 当前位置 右侧的乘积，这样就可以省去记录右侧值的数组了
        int r = 1;
        for (int i = nums.length-1; i >=0 ; i--) {
            ans[i] *= r;
            r*=nums[i];
        }
        return ans;
    }

    /**
     * LC#56
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * @param a
     * @return
     */
    public static int[][]
     mergeIntervals(int [][] a){
        Arrays.sort(a, Comparator.comparingInt(a0 -> a0[0]));
        List<int[]> ans = new ArrayList<>();
        int i = 0;
        while ( i < a.length) {
//            boolean merged = false;
            int j = i+1;
            for(;j<a.length;j++){
                if (a[i][1]>=a[j][0]){
                    a[i][1] = Math.max(a[j][1],a[i][1]);

                }else {
                    break;
                }
            }
//            while(j < a.length && a[i][1]>= a[j][0]){
//                //有重叠时，合并，并继续看下一个是否还有重叠
//                a[i][1] = a[j][1];
//                j++;
//            }
            //合并后，或者无重叠时，就找到一个
            ans.add(a[i]);
            i=j;
        }
        int [][] b= new int[ans.size()][2];
        ans.toArray(b);
        return  b;
    }
    public static int maxProfit(int [] a){
        int ans = 0;
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            ans = Math.max(ans, a[i] - min);
            min = Math.min(min,a[i]);
        }
        return ans;
    }
    int profitr(int [] a, int idx){//recursively
//        return Math.max(a[idx] - minxx(a, idx-1), profit(a, idx-1));
        return 0;
    }
    public static int profitdp(int [] a){
        //aforementioned maxProfit is the optimised version of this general DP
        int[] minxx = new int[a.length];
        minxx[0]=a[0];
        int [] dp = new int[a.length];
        dp[0] = 0;
        int max = 0;
        for(int i =1; i< a.length;i++){
            dp[i] = Math.max(dp[i-1],a[i] - minxx[i-1]);
            max=Math.max(dp[i],max);
            minxx[i ] = Math.min(minxx[i-1],a[i]);//save min till cur day
        }
        return max;

    }

}
