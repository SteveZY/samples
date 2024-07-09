package com.fluentretail.graphql;

import java.util.Arrays;


public class Merge {
    public static void msort(int []a){
        int [] aux =
                Arrays.copyOf(a, a.length);
        mergeSort(aux,a,0,a.length-1);
//        Arrays.sort(null);

    }

    /**
     * 最终结果会存在 aux 中， 因为最后一个merge 是将 a -> aux
     * @param a
     * @param aux
     * @param low
     * @param high
     */
    public static void mergeSort(int[] a, int [] aux, int low, int high){
        if(low>=high){
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(aux,a,low,mid);//交换 a 和 aux
        mergeSort(aux,a,mid+1,high);
//        if(a[mid+1]>=a[mid]) return;
        merge(a,aux,low,mid,high);//
    }
    public static void merge(int[] a, int [] aux, int low, int mid, int high){
//        for (int pos = low; pos <= high; pos++) {
        //也可以每次拷贝， 然后从 aux ->a 合并
//            aux[pos] = a[pos];
//        }
        //交换 aux array 和 a 的role
        int i = low; int j = mid+1;

        for (int k = low; k <=high; k++) {
//此处向 aux 合并， 然后递归调用时，每次都交换 a 和 aux 就不用 拷贝了
            if(i>mid){
                aux[k] = a[j++];
            }else if(j>high){
                aux[k] = a[i++];
            }
            else if (a[j]<a[i]){
                aux[k]=a[j++];
            }else {
                aux[k] = a[i++];
            }
        }
    }


    public static void qsort(int []a){
        quickSort(a,0,a.length-1);
    }

    private static void quickSort(int[] a, int l, int r) {
        if(l>=r) return;
        int m = partition(a,l,r);
        quickSort(a,l,m-1);
        quickSort(a,m+1,r);

    }

    private static int partition(int[] a, int l, int r) {
        int p = a[l];//left as the pivot
        int lIdx = l;
//        int idxLarger ;
        for (int rIdx = lIdx+1; rIdx <= r ; rIdx++) {

            if(a[rIdx]<=p){
                lIdx++;
                if(lIdx == rIdx) continue;
                int tmp = a[lIdx];
                a[lIdx] = a[rIdx];
                a[rIdx] = tmp;
            }
        }
        a[l]=a[lIdx];
        a[lIdx] = p;
        return lIdx;
//        return 0;
    }

}
