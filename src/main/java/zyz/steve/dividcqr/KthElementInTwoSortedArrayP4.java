package zyz.steve.dividcqr;
//https://leetcode.com/problems/median-of-two-sorted-arrays/description/

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 */
public class KthElementInTwoSortedArrayP4 {

    /**
     * Time complexity O(log(m*n)) = O(log(m) + log(n))
     * @param a
     * @param b
     * @param As - A start index
     * @param Ae - A end index
     * @param Bs - B start index
     * @param Be - B end index
     * @param k - the index of the element to be returned
     * @return the element
     */
    public static int findKthElement(int[]a,int[]b, int As, int Ae, int Bs, int Be, int k){
        if(As > Ae ){
            //a数组 从此start处已经被全部丢弃，就是说 已经 有 As 个从a来的元素 位于组合后数组的最前部，而组合后 k 位置之前 共有 k个元素，
            // 还剩余 k-As个元素需要从b 数组出，故这k-As个元素的 最后一个元素的 idx 加上1 就是 k，这个idx = 0(start of idx)+k-As-1，
            // 则k在 b 的序号就是 idx+1 = 0+k-As -1 +1 = k-As;
            return b[k-As];
        }
        if(Bs>Be){
            //此处同上
            return a[k-Bs];
        }
        //开始从中间搜索
        int aidx = (As + Ae) / 2;
        int bidx = (Bs + Be) / 2;
        int amid = a[aidx];
        int bmid = a[bidx];
        //a, b 组合后的 start index 是 As + Bs

        if(k> (aidx+bidx) ){
            //k 较大 在组合后的 右侧， 则必不在 较小数组左侧
            //舍弃 smaller half of a/b
            if(amid>bmid){//b left 较小
                return findKthElement(a,b, As,Ae,bidx+1,Be,k);
            }else {//a left较小
                return findKthElement(a,b, aidx+1,Ae,Bs,Be,k );
            }
        }else{
            //k 较小，必定位于 组合后数组的左侧, 那么肯定不会包含在较大数组的右侧
            // 故舍弃掉 larger half  of a/b
            if(amid>bmid){//a right to be dropped
                return findKthElement(a,b, As,aidx-1, Bs,Be,k);

            }else {
                //b right to be dropped
                return findKthElement(a,b, As,Ae, Bs,bidx-1,k);
            }
        }
    }

    /**
     * 由于 a,b 是排序好的，如果能找到 a smaller part + b smaller part 正好 更 a larger part + b larger part 有相同数量的元素((m+n-1)/2 个 两侧最多差一个)，并且
     * a smaller part 都小于 b larger part ， b smaller part 都小于 a larger part ,那么 median 就 由边界 a left max , a right min
     * 以及 b left max ，b right min 共同决定
     * 如果 m+n是偶数， 则 median = (max(aLmax, bLmax) + min (aRmin, bRmin)) /2
     * 奇数是， 则 median = max (aLmax, bLmax)
     * @param a
     * @param b
     * @return
     */
    public static  double findMedianOptimised(int[]a,int[]b){
        int m = a.length;
        int n = b.length;
        if(m>n){//确保 第一个 数组 较短
            return findMedianOptimised(b,a);
        }
        int left = 0;
        int right = m ;//当全部划分到左侧的时候，需要划分点在最后一个元素后面
        while (left<=right){
            int partA = (left + right) / 2;
            int partB = (m+n+1)/2 - partA;

            int aLmax = partA - 1 < 0 ? Integer.MIN_VALUE : a[partA - 1];
            int aRmin = partA >= m ? Integer.MAX_VALUE : a[partA];//分区用index 包含在右侧；故全部分在左侧是 分区indx 会在最后一个元素后面一个位置
            int bLmax = partB - 1 < 0 ? Integer.MIN_VALUE : b[partB - 1];
            int bRmin = partB >= n ? Integer.MAX_VALUE : b[partB];
            if(aLmax<=bRmin && bLmax <= aRmin){
                //划分正确
                if((m+n)%2==1){
                    return Math.max(aLmax,bLmax);
                }else {
                    return (Math.max(aLmax,bLmax) + Math.min(aRmin,bRmin))/2.0;
                }
            }else if(aLmax>bRmin){
                //划分太靠右了，太偏向大的一侧了，故向左调整
                right = partA - 1;
            } else {
                left = partA + 1;
            }
        }
        return 0.;
    }

}
