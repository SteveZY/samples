package zyz.steve.slidingwin;

public class NumberOfNiceSubArrays {
        // LC 1248 count number of Nice Subarrays
    // 含有 k 个 奇数的 sub arrays 叫做 nice ones
    static int numberOfSubarraysWithExactKoddNums(int [] a, int k) {
        return numberOfSubWithAtMostKoddNums(a,k) - numberOfSubWithAtMostKoddNums(a,k-1);
    }

    static  int numberOfSubWithAtMostKoddNums(int [] a, int k) {
        int ans =0;
        int left =0;
        int countOfOddNums =0;
        for (int i = 0; i < a.length; i++) {
            countOfOddNums += a[i] % 2;
            while (countOfOddNums >k) {
                // shrink window
                countOfOddNums -= a[left]%2;
                left++;
            }
            ans+=i-left+1;

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2};
        System.out.println(numberOfSubWithAtMostKoddNums(arr,1));

    }
}
