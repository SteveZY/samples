package zyz.steve.general;
//https://leetcode.com/problems/nth-digit/description/

import java.util.TreeMap;

/**
 * Given an integer n,
 * return the nth digit of the infinite integer sequence
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].
 */
public class NthDigit {
    public static int  findNthDigit(int n){

        TreeMap<Long,long[]> data = new TreeMap<>();
        long count = 9;
        long digitsTotal = 9;
        data.put(digitsTotal,new long[]{1,count,1});
        int begin = 10;
        for (int i = 1; i < 9; i++) {
//            begin*=10;
            int next = begin * 10;
            count = (next - begin);
            digitsTotal += count*(i+1);
            data.put(digitsTotal,new long[]{begin,count,i+1L});
            begin = next;
            System.out.println(digitsTotal);
        }
        long max = (1L << 31) - 1 - (int)Math.pow(10,9) +1 ;
        System.out.println(max);
        System.out.println(
                digitsTotal+=max*10
        );
        System.out.println(digitsTotal);
        Long floor = data.floorKey((long) n);
        long pos = floor==null?n:n - floor == 0?n:n-floor;//在下一个序列的位置
        long[] xxx = data.get(data.ceilingKey((long)n));
        long realpos = --pos / xxx[2];

        long theNum = xxx[0] + realpos;
        System.out.println(theNum);
        return String.valueOf(theNum).charAt((int) (pos % xxx[2])) -'0';
//        return getDigit(theNum, pos % xxx[2], xxx[2]);
    }
    protected static int getDigit(long num, long pos, long totalDigits){
        int roundTo = (int) Math.pow(10, totalDigits - pos);
        long l = num % roundTo;//仅保留到 所要的位置
        l = l/(int) Math.pow(10, totalDigits - pos-1);
        return (int)l;
    }
    public static  int findItIteratively(int n){
        int k = 1, count=9;
        while ((long)k*count<n){
            //寻找对应的层级 k, k同时也是该层 每个数字的位数
            n -= k*count;
            k++;
            count *= 10;

        }
        int theNum = (int) Math.pow(10, k - 1) + (n - 1) / k;

        return String.valueOf(theNum).charAt((n -1) % k) -'0';

    }

}
