package zyz.steve.array;

//https://leetcode.cn/problems/taking-maximum-energy-from-the-mystic-dungeon/description/
//#3147
// 由于必须 走到最后，需要是求 从哪里开始 最好， 那么从后向前遍历，记录过程中的最大值，一边遍历 就可以了
//O(n)
public class MagicianEngergy {
    public static int maximumEnergy(int[] energy, int k) {
        //从前往后，暴力解法，遍历每一个位置 直到最后， O(n**2) TLE
        //这个问题是 一旦 到 k 位置， 它就根 0 位置 是一组的了，此时就不用向后遍历每一个了，
        // 而是 只需要 记录 本 组 到 下一位置，所能获得的最大值 就行了，
        int ans =Integer.MIN_VALUE;

        for (int i = 0; i < energy.length; i++) {
            int t =0;
            int pos = i;
            while (pos<energy.length){

                t+=energy[pos];
                pos +=k;
            }
            ans = Math.max(ans, t);

        }


        return ans;
    }
    //从前向后 迭代， 避免重复计算
    public static int maximumEnergyForwardIter(int[] energy, int k) {
        //从前往后，暴力解法，遍历每一个位置 直到最后， O(n**2) TLE
        //这个问题是 一旦 到 k 位置， 它就跟 0 位置 是一组的了，此时就不用向后遍历每一个了，
        // 而是 只需要 记录 本 组 到 下一位置，所能获得的最大值 就行了，
        int ans =Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            int t =0;
            int pos = i;
            while (pos<energy.length){
                //DP 的 意思
                t = Math.max(t+energy[pos],energy[pos]); //求本组到当前位置 pos 的最大值
                pos +=k;
            }
            ans = Math.max(ans, t);

        }


        return ans;
    }
    public static int maximumEnergyOP(int[] energy, int k) {
        //从后向前
        int ans =Integer.MIN_VALUE;

        for (int i = energy.length-1; i >= energy.length-k; i--) {
            int t =0 ;
            for (int j = i; j >=0 ; j-=k) {//跳跃 k
                t+=energy[j];
                ans = Math.max(ans, t);//记录到当前位置的最大值
            }

        }


        return ans;
    }
    //TODO： DP

    public static void main(String[] args) {
        System.out.println(maximumEnergy(new int[] {5, 2, -10, -5, 1}, 3));
        System.out.println(maximumEnergyForwardIter(new int[] {5, 2, -10, -5, 1}, 3));
//        System.out.println(maximumEnergyOP(new int[] {5, 2, -10, -5, 1}, 3));
//        System.out.println(maximumEnergyOP(new int[] {-2,-3,-1}, 2));
        System.out.println(maximumEnergyForwardIter(new int[] {-2,-3,-1}, 2));
    }
}
