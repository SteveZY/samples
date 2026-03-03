package zyz.steve.slidingwin;

//https://leetcode.cn/problems/minimum-size-subarray-sum/
public class MinLengthSubArray {

    public static int minLenSubarray(int target , int [] a){
        int ans=Integer.MAX_VALUE;
        int left =0;
        int curSum =0 ;
        for (int i = 0; i <a.length; i++) {
            curSum+=a[i];

            while (curSum >=target){
                ans = Math.min(ans, i - left + 1);
                int leftEle = a[left];
                curSum -= leftEle;
                left++;
            }
        }
        return ans;

    }
    public static void main(String[] args) {
        int t=7;
        int[] a = new int[]{2,3,1,2,4,3};

        System.out.println(minLenSubarray(t, a));
    }
}
