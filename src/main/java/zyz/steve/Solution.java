package zyz.steve;

class Solution {
    public int[] sortedSquares(int[] a) {
        //找到 最小非负值 的index
        int maxIdxOfNeg = findMinNonNegative(a);
        //a new array for result after merge
        int[] ret = new int[a.length];
        int i=maxIdxOfNeg, j = maxIdxOfNeg + 1;

        for (int k = 0; k < a.length; k++) {
            if(i>=0 && j<a.length){

                if(Math.abs(a[i])<=a[j]){
                    ret[k] = a[i]*a[i];
                    i--;
                }else {
                    ret[k] = a[j]*a[j];
                    j++;
                }
                continue;
            }
            if(i<0){
                //negative part exhausted
                ret[k]=a[j]*a[j];
                j++;
            }else {
                //non-negative part exhausted
                ret[k] = a[i]*a[i];
                i--;
            }
        }
        return ret;
    }
    public  int findMinNonNegative(int [] a){
        //a 是非 递减 数组
        int i = 0;
        for (; i < a.length; i++) {
            if(a[i]>=0) {
                break;
            }
        }
        return i -1;
    }
}
