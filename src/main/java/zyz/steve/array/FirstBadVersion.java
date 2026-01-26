package zyz.steve.array;

import java.util.Arrays;

//LC 278  First Bad Version[https://leetcode.com/problems/first-bad-version/description/]
// 二分法 实例
public class FirstBadVersion {
    private static boolean isBadVersion( int k){
        return k>60;
    }
    public static int firstBadVersion(int n){
        int left= 1, right = n;

        // 相当于 在一个数组[ false, false,false,..., true, true, true...] 中
        // 寻找第一个true的位置，
        // 找到最后 也就是 left 和 right 交汇时，left指向的就是第一个 true

        while(left <= right){
            int mid = (left+right)/2;
            if(isBadVersion(mid)){
                right = mid -1 ;// search the left side
            }else {
                left = mid + 1;
            }

        }
        return left;
    }
    // LC 410 [https://leetcode.com/problems/split-array-largest-sum/]
    // 划分数组 为 k 个 子数组， 使的子数组的 sum 中的最大值 要是所有划分中最小的
    // 思路：只能猜测 子数组 sum 的 最大值，但不能乱猜，下界 就是 数组中的最大值，上界就是所有数加在一起的
    //  然后 利用二分 在 max ～ min 之间寻找 那个 sum的最大值 是在各种划分中，最小的
    // 类似问题：1552， 1482， 1283， 1292
    public static int splitArray(int[] nums, int k){
        int min = Arrays.stream(nums).max().getAsInt();
        int max = Arrays.stream(nums).sum();
        return binSearch(nums,min,max, k);
    }
    private static int binSearch(int [] nums, int low, int high, int m){
        while (low<= high){
            int mid = low + (high-low) /2;
            if(validSubArraySum(nums,m,mid)) high = mid-1; //这个中值可以的话，检查是否可以更小
            else low=mid+1;// 太小了，需要到大的一半查找
        }
        return low;
    }
    private static boolean validSubArraySum(int[] nums, int  m, int subArraySum){
        int curSum =0, count =1;
        for(int num: nums){
            curSum+=num;
            if(curSum > subArraySum) {
                curSum = num;// reset sum to the cur number
                count++;// start a new subarray
                if(count>m) return false;// 太多划分了，不行
            }
        }
        return true;
    }
    public static void main(String[] args){
        System.out.println(firstBadVersion(1600000));

        System.out.println(splitArray(new int[]{1, 4, 4,6,9,4}, 3));
    }
}
