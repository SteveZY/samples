package zyz.steve.array;

//https://leetcode.com/problems/special-array-ii/description/
//#3152
public class SpecialArrayII {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        //prefix sum

        boolean [] ans = new boolean[queries.length];
        int [] ps = new int[nums.length];
        for(int i =1; i< nums.length; i++){
            ps[i]=ps[i-1];//remember the pairs of consecutive nums with the same parity
            if(nums[i-1]%2 == nums[i] %2){
                ps[i]++;
            }
        }
        for(int i=0;i<queries.length;i++ ){
            int s = queries[i][0];
            int e = queries[i][1];
            ans[i] = ps[s]==ps[e]? true:false;

        }
        return ans;

    }

    public boolean is(int[] nums,int s, int e) {

        if(e-s < 1) return true;
        int r = nums[s]&1;
        for(int i =s+2; i<= e;i+=2){
            // int newr = nums[i]&1;
            if((nums[i]&1) !=r ) return false;

        }
        for(int i=s+1;i<=e;i+=2){
            if((nums[i]&1) ==r ) return false;


        }
        return true;
    }
}
