package zyz.steve.array;

import java.util.Arrays;

//https://leetcode.cn/problems/sum-of-digit-differences-of-all-pairs/description/
//3153
public class DigitDiff {
    public static long sumDigitDifferences(int[] nums) {
        //1.遍历每一位
        //2. 每一位值，记录 0～9 出现次数
        //3. cnt[m] * cnt[n]  (0=<m,n<=9 ) 为 数字 m和n 在当前数位 出现的次数 组合数，即为 这二者对数位不同做出的贡献，遍历cnt 数组， 累加所有组合数 即为答案
        //4. 返回 1 计算下一个位置
        int l = Integer.toString(nums[0]).length();// 总位数
        long total =0;//记录答案
        int [] cnt = new int[10];//记录 0 ～ 9 出现的次数
        //开始数位遍历
        for (int i = 0; i < l; i++) {//i 记录当前数位， 最低位 为 0 ，最高为 l-1
            for (int j = 0; j < nums.length; j++) {
                //获取 当前数字的 i 位置 digit
                int di = (nums[j] /(int)Math.pow(10,i)) %10;
                cnt[di] ++;

            }
            //遍历 cnt 计算当前 第 i 位置 diff
            for (int m = 0; m < 10; m++) {
                for (int n = m+1; n < 10; n++) {//从 下一个位置开始
                    total += (long) cnt[m] * cnt[n];
                }

            }
            //next round , reset counter
            Arrays.fill(cnt, 0);
        }
        return total;

    }

    public static void main(String[] args) {
        System.out.println(sumDigitDifferences(new int[] {13, 23, 12}));
    }

}
