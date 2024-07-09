package zyz.steve.dp;

import java.util.*;

//https://leetcode.com/problems/find-subarray-with-bitwise-and-closest-to-k/description/
//#3171
public class BitwiseAndMinDiff {

    //https://leetcode.com/problems/find-subarray-with-bitwise-and-closest-to-k/solutions/5244304/contest-400-hard-solution/
    public static int minimumDifferenceOP(int[] nums, int k) {
        int n                 = nums.length;
        int min               = Integer.MAX_VALUE;
        HashSet<Integer> set  = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            // check min right away
            min = Math.min(min, Math.abs(k - num));

            // check accumulated ANDs of subarray with value > k;
            HashSet<Integer> set2 = new HashSet<>();
            if (num > k) set2.add(num);
            for (int a : set) {
                int b = a & num;
                min = Math.min(min, Math.abs(k - b));
                if (b > k) set2.add(b);
            }

            // update the accumulating set
            set = set2;
        }

        return min;
    }
    public static int minimumDifference(int[] nums, int k) {
        List<Integer> dp = new ArrayList<>();

//        dp[0] = new ArrayList();
//        dp.add(nums[0]);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                Integer cur ;
                if(j >= dp.size()){
                    cur=0x7fffffff;
                }else {
                    cur= dp.get(j);

                }
                if(cur ==0) continue;
                int next = cur & nums[i];
                ans = Math.min(ans, Math.abs(k-next));
//                if(next==0) continue;
                if (j >= dp.size()) {
                    dp.add(next);
                } else {
                    dp.set(j, next);
                }

            }
        }

        return ans;


    }

    public static void main(String[] args) {
        /**
         * 001
         * 010
         * 100
         * 101
         */
        System.out.println(minimumDifference(new int[] {1, 2, 4, 5}, 3));
        System.out.println(minimumDifference(new int[] {1,2,1,2}, 2));
        System.out.println(minimumDifference(new int[] {1}, 10));


        System.out.println(minimumDifferenceOP(new int[] {1, 2, 4, 5}, 3));
        System.out.println(minimumDifferenceOP(new int[] {1,2,1,2}, 2));
        System.out.println(minimumDifferenceOP(new int[] {1}, 10));
    }
}
